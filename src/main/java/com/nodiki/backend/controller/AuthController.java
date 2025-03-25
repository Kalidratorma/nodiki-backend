package com.nodiki.backend.controller;

import com.nodiki.backend.dto.*;
import com.nodiki.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public void register(@RequestBody RegisterRequest request) {
    authService.register(request);
  }

  @PostMapping("/login")
  public JwtResponse login(@RequestBody LoginRequest request) {
    String token = authService.login(request);
    return new JwtResponse(token);
  }
}
