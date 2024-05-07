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
        http.cors();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/auth/**","/time/**","/options/**","/mail/**", "/oauth2/**","/notification/**","/review/top","/account/principal")
                .permitAll()
                .antMatchers("/reservation/user/**", "/trainer/user/**","review/user/**",
                        "/holiday/user/**","/inbody/user/**","/routine/user/**","account/user/**")
                .hasRole("USER")
                .antMatchers("/auth/trainer/**", "/reservation/trainer/**", "/trainer/trainer/**","review/trainer/**",
                        "/holiday/trainer/**","/inbody/trainer/**","/routine/trainer/**","account/trainer/**")
                .hasRole("TRAINER")
                .antMatchers("/auth/admin/**", "/reservation/admin/**", "/trainer/admin/**","review/admin/**",
                        "/holiday/admin/**","/inbody/admin/**","/routine/admin/**","account/admin/**")
                .hasRole("ADMIN")
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
