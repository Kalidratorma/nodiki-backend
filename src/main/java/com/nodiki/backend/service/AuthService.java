package com.nodiki.backend.service;

import com.nodiki.backend.dto.*;
import com.nodiki.backend.model.*;
import com.nodiki.backend.repository.*;
import com.nodiki.backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  public void register(RegisterRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new RuntimeException("Username already exists");
    }

    User user =
        User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

    Role role =
        roleRepository
            .findByName(Role.ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Default role not found"));

    user.getRoles().add(role);
    userRepository.save(user);
  }

  public String login(LoginRequest request) {
    User user =
        userRepository
            .findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }

    return jwtTokenProvider.generateToken(user.getUsername());
  }
}
