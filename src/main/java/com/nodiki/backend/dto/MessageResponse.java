package com.nodiki.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Simple message response for success or error notifications")
public class MessageResponse {

    @Schema(description = "Message content", example = "User registered successfully!")
    private String message;
}
