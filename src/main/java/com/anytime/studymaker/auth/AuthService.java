package com.anytime.studymaker.auth;

import com.anytime.studymaker.controller.dto.TokenDto;

public interface AuthService {
    TokenDto reissueToken(String refreshToken);
}
