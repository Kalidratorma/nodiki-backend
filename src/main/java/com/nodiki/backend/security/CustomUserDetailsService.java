package com.nodiki.backend.security;

import com.nodiki.backend.model.User;
import com.nodiki.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found: " + username));

    return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities(
            user.getRoles().stream().map(role -> role.getName().name()).toArray(String[]::new))
        .build();
  }
}
