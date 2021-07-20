package com.anytime.studymaker.auth.jwt;

import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.cache.Token;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;


public interface TokenProvider extends InitializingBean {

    Token publishToken(Authentication authentication);
    Authentication getAuthentication(String token);
    boolean validateToken(String token);
    String createAccessToken(User user);
    String createRefreshToken(User user);
}
