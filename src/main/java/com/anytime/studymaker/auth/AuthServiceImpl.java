package com.anytime.studymaker.auth;

import com.anytime.studymaker.auth.jwt.TokenProvider;
import com.anytime.studymaker.controller.dto.LoginRequest;
import com.anytime.studymaker.controller.dto.LoginResponse;
import com.anytime.studymaker.controller.dto.TokenResponse;
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
    public LoginResponse signIn(LoginRequest request, AuthenticationManager manager) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authentication = manager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        Token jwt = tokenProvider.publishToken(authentication);
        TokenResponse tokenResponse = TokenResponse.builder()
                .access(jwt.getAccessToken()).refresh(jwt.getRefreshToken())
                .build();

        LoginResponse response = LoginResponse.builder()
                .token(tokenResponse).email(user.getEmail())
                .name(user.getName()).nickname(user.getNickname())
                .userId(user.getUserId()).build();
        return response;
    }

    @Override
    public TokenResponse reissueToken(String refreshToken) {
        Token outdatedToken = tokenRepository.findById(refreshToken).orElseThrow();
        User user = userRepository.findById(outdatedToken.getUserId()).orElseThrow();

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), "", user.getAuthorities());
        Token reissuedToken = tokenProvider.publishToken(authentication);
        tokenRepository.delete(outdatedToken);
        tokenRepository.save(reissuedToken);

        TokenResponse response = TokenResponse.builder()
                .access(reissuedToken.getAccessToken())
                .refresh(reissuedToken.getRefreshToken())
                .build();

        return response;
    }
}
