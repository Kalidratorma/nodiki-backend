package com.nodiki.backend.controller;

import com.nodiki.backend.model.Node;
import com.nodiki.backend.repository.NodeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nodes")
public class NodesController {

    private final NodeRepository nodeRepository;

    public NodesController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @GetMapping
    public List<Node> getAllNodes() {
        return nodeRepository.findAll();
    }

    @PostMapping
    public Node createNode(@RequestBody Node node) {
        return nodeRepository.save(node);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        nodeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
