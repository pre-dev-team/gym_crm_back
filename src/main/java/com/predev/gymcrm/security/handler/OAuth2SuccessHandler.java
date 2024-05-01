package com.predev.gymcrm.security.handler;

import com.predev.gymcrm.entity.Account;
import com.predev.gymcrm.jwt.JwtProvider;
import com.predev.gymcrm.repository.AuthMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${client.deploy-address}")
    private String clientAddress;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String name = authentication.getName();
        Account account = authMapper.findAccountByOAuth2Name(name);

        // OAuth2 로그인을 통해 회원가입이 되어있지 않은 상태
        // OAuth2 동기화
        if(account == null) {
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
            String providerName = oAuth2User.getAttribute("provider").toString();

            response.sendRedirect("http://" + clientAddress + "/auth/oauth2/signup?name=" + name + "&provider=" + providerName);
            return;
        }

        // OAuth2 로그인을 통해 회원가입을 진행한 기록이 있는 상태
        String accessToken = jwtProvider.generateJwt(account);
        response.sendRedirect("http://" + clientAddress + "/auth/oauth2/merge?accessToken=" + accessToken);




    }
}