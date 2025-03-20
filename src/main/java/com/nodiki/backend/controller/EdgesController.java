package com.nodiki.backend.controller;

import com.nodiki.backend.model.Edge;
import com.nodiki.backend.repository.EdgeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edges")
public class EdgesController {

    private final EdgeRepository edgeRepository;

    public EdgesController(EdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    @GetMapping
    public List<Edge> getAllEdges() {
        return edgeRepository.findAll();
    }

    @PostMapping
    public Edge createEdge(@RequestBody Edge edge) {
        return edgeRepository.save(edge);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdge(@PathVariable Long id) {
        edgeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
