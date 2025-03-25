package com.nodiki.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter that intercepts incoming HTTP requests and checks for a valid JWT token.
 * If the token is valid, it sets the authentication context so that Spring Security
 * recognizes the request as authenticated.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Service for extracting and validating JWT tokens.
     */
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Service to load user details by username.
     */
    private final CustomUserDetailsService userDetailsService;

    /**
     * Filters every HTTP request to check for a JWT token in the Authorization header.
     * If a valid token is found, the user is authenticated in the Spring Security context.
     *
     * @param request the incoming HTTP request
     * @param response the outgoing HTTP response
     * @param filterChain the filter chain to continue processing
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error is detected
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            String username = jwtTokenProvider.getUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
