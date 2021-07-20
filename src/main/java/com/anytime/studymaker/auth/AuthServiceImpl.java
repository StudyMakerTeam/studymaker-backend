package com.anytime.studymaker.auth;

import com.anytime.studymaker.auth.jwt.TokenProvider;
import com.anytime.studymaker.controller.dto.TokenDto;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.cache.Token;
import com.anytime.studymaker.domain.user.cache.RefreshTokenRepository;
import com.anytime.studymaker.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final RefreshTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Override
    public TokenDto reissueToken(String refreshToken) {
        Token token = tokenRepository.findById(refreshToken).orElseThrow();
        User user = userRepository.findById(token.getUserId()).orElseThrow();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), "", user.getAuthorities());
        Token reissuedToken = tokenProvider.publishToken(authentication);

        tokenRepository.delete(token);
        tokenRepository.save(reissuedToken);

        TokenDto reissuedTokenDto = TokenDto.builder()
                .access(reissuedToken.getAccessToken())
                .refresh(reissuedToken.getRefreshToken())
                .build();

        return reissuedTokenDto;
    }
}
