package com.nodiki.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a Node entity on the board.")
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the node", example = "1")
    private Long id;

    @Schema(description = "Label or text of the node", example = "My Node")
    private String label;

    @Schema(description = "X coordinate of the node on the board", example = "100.0")
    private double x;

    @Schema(description = "Y coordinate of the node on the board", example = "200.0")
    private double y;
}
