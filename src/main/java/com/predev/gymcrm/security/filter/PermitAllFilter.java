package com.predev.gymcrm.security.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class PermitAllFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        List<String> antMatchers = List.of(
                "/auth/user","/auth/oauth2","/time","/options","/mail", "/oauth2","/review/top"
        );
        boolean isPermitAll = antMatchers.stream().anyMatch(uri::startsWith);
        request.setAttribute("isPermitAll", isPermitAll);

        filterChain.doFilter(request, response);
    }
}
