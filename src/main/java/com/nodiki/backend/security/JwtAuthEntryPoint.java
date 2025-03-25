package com.nodiki.backend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Handles unauthorized access attempts by returning 401 Unauthorized response.
 * Used by Spring Security when a user tries to access a secured REST endpoint without authentication.
 */
@Slf4j
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * This method is triggered when an unauthenticated user tries to access a secured REST endpoint.
     *
     * @param request       the HttpServletRequest
     * @param response      the HttpServletResponse
     * @param authException the exception that caused the invocation
     * @throws IOException      in case of input/output errors
     * @throws ServletException in case of servlet errors
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        log.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}
