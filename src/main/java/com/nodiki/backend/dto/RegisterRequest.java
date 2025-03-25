package com.nodiki.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request body for user registration")
public class RegisterRequest {

  @Schema(description = "Unique username", example = "nodemaster")
  @NotBlank
  @Size(min = 3, max = 50)
  private String username;

  @Schema(description = "Password (min 6 characters)", example = "S3cret!")
  @NotBlank
  @Size(min = 6, max = 100)
  private String password;

  @Schema(description = "Valid email address", example = "node@example.com")
  @NotBlank
  @Email
  private String email;
}
