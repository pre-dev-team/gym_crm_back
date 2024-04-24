package com.predev.gymcrm.security.filter;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Component
public class MailSessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        long expireTime = new Date(new Date().getTime() + (1000 * 60 * 3)).getTime();
        long lastReqDateTime = ((Date)session.getAttribute("date")).getTime();
        long nowTime = new Date().getTime();

        if(nowTime - lastReqDateTime < expireTime) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

        }

        filterChain.doFilter(request, response);
    }
}