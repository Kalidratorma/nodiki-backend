package com.nodiki.backend.controllers;

import com.nodiki.backend.entities.Edge;
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
}
