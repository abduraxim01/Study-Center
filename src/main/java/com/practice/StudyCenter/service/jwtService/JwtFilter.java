package com.practice.StudyCenter.service.jwtService;

import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.authService.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    final private Logger logger = LogManager.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        String userName = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
//            userName = jwtUtil.extractClaims(token).getSubject();
            try {
                userName = jwtUtil.extractClaims(token).getSubject();
            } catch (ExpiredJwtException e) {
                logger.error("JWT token has expired: {}", e.getMessage());
                throw new AllExceptions.InvalidJwtException("Jwt token eskirgan: from dofilter");
                 // Stop the filter chain
            }
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                UserDetails userDetails = authService.loadUserByUsername(userName);

                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (ExpiredJwtException | MalformedJwtException | SignatureException |
                     IllegalArgumentException exception) {
                throw new AllExceptions.InvalidJwtException("JWT xato yoki eskirgan: " + exception.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
