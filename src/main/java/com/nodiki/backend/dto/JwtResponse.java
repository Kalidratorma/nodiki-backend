package com.nodiki.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Response returned after successful authentication")
public class JwtResponse {

  @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
  private String token;

  @Schema(description = "Token type, usually 'Bearer'", example = "Bearer")
  private String type = "Bearer";

  @Schema(description = "Unique username", example = "nodemaster")
  private String username;

  @Schema(description = "User email", example = "node@example.com")
  private String email;

  @Schema(description = "List of roles assigned to the user", example = "[\"ROLE_USER\"]")
  private List<String> roles;
}
