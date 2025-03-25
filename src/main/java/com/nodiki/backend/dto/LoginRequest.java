package com.nodiki.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for user login")
public class LoginRequest {

  @Schema(description = "Username or email", example = "nodemaster")
  @NotBlank
  private String username;

  @Schema(description = "User password", example = "S3cret!")
  @NotBlank
  private String password;
}
