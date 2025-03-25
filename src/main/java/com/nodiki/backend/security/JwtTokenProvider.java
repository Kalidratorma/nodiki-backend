package com.nodiki.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * Provides utility methods for creating, validating, and parsing JWT tokens.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

  /**
   * Secret key used to sign the JWT tokens. Configured via application properties.
   */
  @Value("${jwt.secret}")
  private String jwtSecret;

  /**
   * JWT token expiration time in milliseconds. Configured via application properties.
   */
  @Value("${jwt.expiration}")
  private long jwtExpiration;

  /**
   * Cryptographic key derived from {@code jwtSecret}, used to sign and validate tokens.
   */
  private Key key;

  /**
   * Initializes the secret key after the component is constructed.
   * This method decodes the base64-encoded secret and prepares the key for signing.
   */
  @PostConstruct
  protected void init() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  /**
   * Generates a signed JWT token for the given user.
   *
   * @param username the username of the authenticated user
   * @param roles a list of roles assigned to the user
   * @return a signed JWT token string
   */
  public String createToken(String username, List<String> roles) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);

    Date now = new Date();
    Date expiry = new Date(now.getTime() + jwtExpiration);

    return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
  }

  /**
   * Extracts the username (subject) from the JWT token.
   *
   * @param token the JWT token string
   * @return the username from the token's subject field
   */
  public String getUsername(String token) {
    return parseClaims(token).getSubject();
  }

  /**
   * Validates a JWT token by checking its structure, signature, and expiration.
   *
   * @param token the JWT token to validate
   * @return {@code true} if the token is valid; {@code false} otherwise
   */
  public boolean validateToken(String token) {
    try {
      parseClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * Resolves the token from the {@code Authorization} header in the HTTP request.
   *
   * @param request the HTTP servlet request
   * @return the extracted JWT token string, or {@code null} if not present
   */
  public String resolveToken(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    if (bearer != null && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }

  /**
   * Parses and returns all claims from the given JWT token.
   *
   * @param token the JWT token string
   * @return the {@link Claims} extracted from the token
   */
  private Claims parseClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
}
