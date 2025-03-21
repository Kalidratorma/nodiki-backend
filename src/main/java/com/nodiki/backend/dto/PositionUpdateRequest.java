package com.nodiki.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request DTO for updating node position.")
public class PositionUpdateRequest {

    @Schema(description = "New X coordinate", example = "100.0")
    private double x;

    @Schema(description = "New Y coordinate", example = "200.0")
    private double y;
}
