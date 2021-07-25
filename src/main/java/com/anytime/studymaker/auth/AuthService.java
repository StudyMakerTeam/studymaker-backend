package com.anytime.studymaker.auth;

import com.anytime.studymaker.controller.dto.LoginRequest;
import com.anytime.studymaker.controller.dto.LoginResponse;
import com.anytime.studymaker.controller.dto.TokenResponse;
import org.springframework.security.authentication.AuthenticationManager;

public interface AuthService {
    LoginResponse signIn(LoginRequest request, AuthenticationManager manager);
    TokenResponse reissueToken(String refreshToken);
}
