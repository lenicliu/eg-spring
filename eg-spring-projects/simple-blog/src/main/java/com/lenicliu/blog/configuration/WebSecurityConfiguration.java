package com.lenicliu.blog.configuration;

import com.lenicliu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lenicliu on 11/10/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http // 基础配置
                .formLogin().and()
                .logout().and()
                .httpBasic().disable()
                .cors().disable()
                .csrf().disable()
                .x509().disable();
        http // 权限配置
                .authorizeRequests().antMatchers("/admin/*", "/metrics/*").authenticated().and()
                .authorizeRequests().antMatchers("/login", "/j_spring_security_check", "/webjars/**").permitAll();
        http // 会话配置
                .sessionManagement().maximumSessions(1);
    }
}