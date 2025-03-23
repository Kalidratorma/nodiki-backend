package com.nodiki.backend.controller;

import com.nodiki.backend.model.Edge;
import com.nodiki.backend.repository.EdgeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/edges")
@Tag(name = "Edges", description = "API for managing graph edges")
public class EdgeController {

  private final EdgeRepository edgeRepository;

  public EdgeController(EdgeRepository edgeRepository) {
    this.edgeRepository = edgeRepository;
  }

  @GetMapping
  @Operation(summary = "Get all edges", description = "Returns a list of all edges in the graph")
  public List<Edge> getAllEdges() {
    return edgeRepository.findAll();
  }

  @PostMapping
  @Operation(summary = "Create a new edge", description = "Creates a new edge between two nodes")
  public Edge createEdge(@RequestBody Edge edge) {
    return edgeRepository.save(edge);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an edge", description = "Updates the description of an edge")
  public ResponseEntity<Edge> updateEdge(@PathVariable Long id, @RequestBody Edge updatedEdge) {
    return edgeRepository
        .findById(id)
        .map(
            edge -> {
              edge.setDescription(updatedEdge.getDescription());
              return ResponseEntity.ok(edgeRepository.save(edge));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete an edge", description = "Removes an edge from the graph")
  public ResponseEntity<Void> deleteEdge(@PathVariable Long id) {
    edgeRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
