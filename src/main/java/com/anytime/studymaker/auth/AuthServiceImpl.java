package com.anytime.studymaker.auth;

import com.anytime.studymaker.auth.jwt.TokenProvider;
import com.anytime.studymaker.controller.dto.LoginRequest;
import com.anytime.studymaker.controller.dto.LoginResponse;
import com.anytime.studymaker.controller.dto.TokenDto;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.cache.Token;
import com.anytime.studymaker.domain.user.UserRepository;
import com.anytime.studymaker.domain.user.cache.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    @Override
    public LoginResponse signIn(LoginRequest request, AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getDetails();
        Token jwt = tokenProvider.publishToken(authentication);
        TokenDto tokenDto = TokenDto.builder()
                .access(jwt.getAccessToken()).refresh(jwt.getRefreshToken())
                .build();

        LoginResponse response = LoginResponse.builder()
                .token(tokenDto).email(user.getEmail())
                .name(user.getName()).nickname(user.getNickname())
                .userId(user.getUserId()).build();
        return response;
    }

    @Override
    public TokenDto reissueToken(String refreshToken) {
        Token token = tokenRepository.findById(refreshToken).orElseThrow();
        User user = userRepository.findById(token.getUserId()).orElseThrow();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), "", user.getAuthorities());
        Token reissuedToken = tokenProvider.publishToken(authentication);
        tokenRepository.delete(token);

        TokenDto reissuedTokenDto = TokenDto.builder()
                .access(reissuedToken.getAccessToken())
                .refresh(reissuedToken.getRefreshToken())
                .build();

        return reissuedTokenDto;
    }
}
