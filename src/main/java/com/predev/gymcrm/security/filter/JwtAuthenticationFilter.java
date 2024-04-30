package com.predev.gymcrm.security.filter;

import com.predev.gymcrm.exception.JwtException;
import com.predev.gymcrm.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;
    private static final String AUTHORIZATION_NAME_IN_HEADER = "Authorization";
    private static final String PREFIX_OF_TOKEN = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isPermitAll = (boolean) request.getAttribute("isPermitAll");

        if (!isPermitAll) {
            String authorizationHeader = request.getHeader(AUTHORIZATION_NAME_IN_HEADER);
            if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX_OF_TOKEN)) {
                String token = authorizationHeader.substring(PREFIX_OF_TOKEN.length());
                try {
                    Claims claims = jwtProvider.getClaims(token);
                    Authentication authentication = jwtProvider.getAuthentication(claims);

                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        response.sendError(HttpStatus.UNAUTHORIZED.value()); // 인증 실패
                        return;
                    }
                } catch (Exception e) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value()); // 토큰 검증 실패
                    return;
                }
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value()); // 토큰 없음
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
