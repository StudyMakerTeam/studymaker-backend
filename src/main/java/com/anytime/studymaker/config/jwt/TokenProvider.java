package com.anytime.studymaker.config.jwt;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;


public interface TokenProvider extends InitializingBean {

    String createToken(Authentication authentication);
    Authentication getAuthentication(String token);
    boolean validateToken(String token);
}
