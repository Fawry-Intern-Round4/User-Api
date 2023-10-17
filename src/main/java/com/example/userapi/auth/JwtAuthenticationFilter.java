package com.example.userapi.auth;

import com.example.userapi.enums.Messages;
import com.example.userapi.service.UserService;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtUtil jwtUtil;

    private void addUserDetailsToContextIfValid(String username,String token) throws AuthException {
        UserDetails userDetails = userService.userDetailsService()
                .loadUserByUsername(username);
        if (!jwtUtil.isTokenValid(token, userDetails)) {
            throw new AuthException(Messages.TOKEN_NOT_VALID.getMessage());
        }
        if (!userDetails.isEnabled()) {
            throw new AuthException(Messages.USER_NOT_ENABLE.getMessage());
        }
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals("/user/login") && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new AuthException(Messages.AUTHORIZATION_HEADER_NOT_FOUND.getMessage());
            }
            jwt = authHeader.substring(7);
            username = jwtUtil.extractUserName(jwt);
            if (username == null) {
                throw new AuthException(Messages.USERNAME_NOT_FOUND_IN_TOKEN.getMessage());
            }
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                addUserDetailsToContextIfValid(username,jwt);
            }
        } catch (Exception ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
            return;
        }

        filterChain.doFilter(request, response);
    }
}