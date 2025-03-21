package com.nodiki.backend.controller;

import com.nodiki.backend.dto.PositionUpdateRequest;
import com.nodiki.backend.model.Node;
import com.nodiki.backend.repository.NodeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nodes")
@Tag(name = "Nodes", description = "Operations related to nodes on the board.")
public class NodeController {

    private final NodeRepository nodeRepository;

    public NodeController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Operation(summary = "Get all nodes")
    @GetMapping
    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    @Operation(summary = "Create a new node")
    @PostMapping
    public Node createNode(@RequestBody Node node) {
        return nodeRepository.save(node);
    }

    @Operation(summary = "Clear all nodes")
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearNodes() {
        nodeRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update node label by id")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNodeLabel(
            @PathVariable Long id,
            @RequestBody Node nodeRequest
    ) {
        Optional<Node> nodeOptional = nodeRepository.findById(id);
        if (nodeOptional.isPresent()) {
            Node node = nodeOptional.get();
            node.setLabel(nodeRequest.getLabel());
            nodeRepository.save(node);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update node position by id")
    @PutMapping("/{id}/position")
    public ResponseEntity<Void> updateNodePosition(
            @PathVariable Long id,
            @RequestBody PositionUpdateRequest position
    ) {
        Optional<Node> nodeOptional = nodeRepository.findById(id);
        if (nodeOptional.isPresent()) {
            Node node = nodeOptional.get();
            node.setX(position.getX());
            node.setY(position.getY());
            nodeRepository.save(node);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete node by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        if (nodeRepository.existsById(id)) {
            nodeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
