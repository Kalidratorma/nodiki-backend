package com.nodiki.backend.controller;

import com.nodiki.backend.dto.JwtResponse;
import com.nodiki.backend.dto.LoginRequest;
import com.nodiki.backend.dto.MessageResponse;
import com.nodiki.backend.dto.RegisterRequest;
import com.nodiki.backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Handles user registration and login")
public class AuthController {

  private final AuthService authService;

  /**
   * Registers a new user in the system.
   *
   * @param request the registration data
   * @return success or error message
   */
  @Operation(summary = "Register a new user")
  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
    MessageResponse response = authService.registerUser(request);
    return ResponseEntity.ok(response);
  }

  /**
   * Authenticates a user and returns a JWT token.
   *
   * @param request the login credentials
   * @return JWT and user info
   */
  @Operation(summary = "Authenticate user and get JWT token")
  @PostMapping("/signin")
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest request) {
    JwtResponse response = authService.authenticateUser(request);
    return ResponseEntity.ok(response);
  }
}
