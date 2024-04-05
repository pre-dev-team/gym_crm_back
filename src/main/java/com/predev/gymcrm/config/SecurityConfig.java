package com.predev.gymcrm.config;

import com.predev.gymcrm.security.exception.AuthEntryPoint;
import com.predev.gymcrm.security.filter.JwtAuthenticationFilter;
import com.predev.gymcrm.security.filter.PermitAllFilter;
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
    PermitAllFilter permitAllFilter;

    @Autowired
    AuthEntryPoint authEntryPoint;

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
                .antMatchers("/admin/**","/common/**","/reservation/**")
                .permitAll()
                .antMatchers("/user/**")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter(permitAllFilter, LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint);
    }
}
