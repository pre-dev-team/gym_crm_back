package com.predev.gymcrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //web mvc cors 설정 가져옴
        http.cors();
        //jwt 토큰으로 인증할것이기 때문에 csrf 비활성
        http.csrf().disable();
        //우선 모든 요청에 대해 열어두겠습니다.
        http.authorizeRequests()
                .anyRequest()
                .permitAll();
    }
}
