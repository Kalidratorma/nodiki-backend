package com.nodiki.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for testing access to protected resources using JWT authentication.
 * This controller provides endpoints that are accessible only to authenticated users with appropriate roles.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
@Tag(name = "Test", description = "Endpoints for testing JWT protected access")
public class TestController {

    /**
     * Returns a simple greeting message.
     * This endpoint is protected and requires the user to have the "ROLE_USER" authority.
     *
     * @return a welcome message for authenticated users
     */
    @Operation(summary = "Greet authenticated user", description = "Returns a greeting message for users with ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, authenticated user!");
    }
}
