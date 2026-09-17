package com.web.sms.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtFilter(
            JwtUtil jwtUtil,
            UserDetailsService userDetailsService) {

        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Read the Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2. Check whether the header contains a Bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extract the JWT token
        String token = authHeader.substring(7);

        String username;

        try
        {
            // 4. Extract username from the JWT
            username = jwtUtil.extractUsername(token);

        }
        catch (Exception e)
        {

            // Invalid token
            filterChain.doFilter(request, response);
            return;
        }

        // 5. Check whether authentication already exists
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {

            // 6. Load user details from your UserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 7. Validate the JWT
            if (jwtUtil.validateToken(token, userDetails))
            {

                // 8. Create an authenticated user object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 9. Add request details
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 10. Store authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 11. Continue the request
        filterChain.doFilter(request, response);
    }
}