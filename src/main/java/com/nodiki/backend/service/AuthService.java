package com.nodiki.backend.service;

import com.nodiki.backend.dto.JwtResponse;
import com.nodiki.backend.dto.LoginRequest;
import com.nodiki.backend.dto.MessageResponse;
import com.nodiki.backend.dto.RegisterRequest;
import com.nodiki.backend.model.Role;
import com.nodiki.backend.model.Role.ERole;
import com.nodiki.backend.model.User;
import com.nodiki.backend.repository.RoleRepository;
import com.nodiki.backend.repository.UserRepository;
import com.nodiki.backend.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  /**
   * Registers a new user in the system.
   *
   * @param request the registration request payload
   * @return success or error message
   */
  @Operation(summary = "Register a new user")
  @Transactional
  public MessageResponse registerUser(RegisterRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      return new MessageResponse("Error: Username is already taken!");
    }

    if (userRepository.existsByEmail(request.getEmail())) {
      return new MessageResponse("Error: Email is already in use!");
    }

    User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role USER not found"));
    roles.add(userRole);

    user.setRoles(roles);
    userRepository.save(user);

    return new MessageResponse("User registered successfully!");
  }

  /**
   * Authenticates a user and generates JWT token.
   *
   * @param request the login request payload
   * @return JWT response with token and user info
   */
  @Operation(summary = "Authenticate user and return JWT token")
  public JwtResponse authenticateUser(LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    );

    User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("Error: User not found"));

    String jwt = jwtTokenProvider.createToken(
            user.getUsername(),
            user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList())
    );

    return new JwtResponse(
            jwt,
            "Bearer",
            user.getUsername(),
            user.getEmail(),
            user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList())
    );
  }
}
