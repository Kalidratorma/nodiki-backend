package com.nodiki.backend.controller;

import com.nodiki.backend.model.Node;
import com.nodiki.backend.repository.NodeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nodes")
@Tag(name = "Nodes", description = "API for managing graph nodes")
public class NodeController {

    private final NodeRepository nodeRepository;

    public NodeController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @GetMapping
    @Operation(summary = "Get all nodes", description = "Returns a list of all nodes in the graph")
    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a new node", description = "Creates a new graph node and returns it")
    public Node createNode(@RequestBody Node node) {
        return nodeRepository.save(node);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a node", description = "Updates the label and position of a node")
    public ResponseEntity<Node> updateNode(@PathVariable Long id, @RequestBody Node updatedNode) {
        return nodeRepository.findById(id)
                .map(node -> {
                    node.setLabel(updatedNode.getLabel());
                    node.setX(updatedNode.getX());
                    node.setY(updatedNode.getY());
                    nodeRepository.save(node);
                    return ResponseEntity.ok(node);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a node", description = "Removes a node from the graph")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        nodeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
