package com.predev.gymcrm.config;

import com.predev.gymcrm.security.exception.AuthEntryPoint;
import com.predev.gymcrm.security.filter.JwtAuthenticationFilter;
import com.predev.gymcrm.security.filter.MailSessionFilter;
import com.predev.gymcrm.security.filter.PermitAllFilter;
import com.predev.gymcrm.security.handler.OAuth2SuccessHandler;
import com.predev.gymcrm.service.OAuth2PrincipalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    MailSessionFilter mailSessionFilter;

    @Autowired
    PermitAllFilter permitAllFilter;

    @Autowired
    AuthEntryPoint authEntryPoint;

    @Autowired
    OAuth2PrincipalUserService oAuth2PrincipalUserService;
    @Autowired
    OAuth2SuccessHandler oAuth2SuccessHandler;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //web mvc cors 설정 가져옴
        http.cors();
        //jwt 토큰으로 인증할것이기 때문에 csrf 비활성
        http.csrf().disable();
        //auth로 받는 모든 요청제외하고 jwt인가받겠습니다
        http.authorizeRequests()
                .antMatchers("/auth/**","/admin/**","/common/**",
                        "/reservation/**","/trainer/**", "/review/**",
                        "/options/**", "/holiday/**", "/inbody/**",
                        "/routine/**","/mail/**", "/oauth2/**","/notification/**")
                .permitAll()
                .antMatchers("/user/**")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter(permitAllFilter, LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(mailSessionFilter,UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                .userService(oAuth2PrincipalUserService);
    }
}
