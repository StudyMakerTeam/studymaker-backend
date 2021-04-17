package com.anytime.studymaker.config.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider provider;

    public JwtSecurityConfig(TokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    }
}
