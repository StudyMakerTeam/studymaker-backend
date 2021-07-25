package com.anytime.studymaker.auth.jwt;

import com.anytime.studymaker.auth.AuthService;
import com.anytime.studymaker.controller.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String REFRESH_TOKEN = "Refresh";

    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String access = resolveAccess(request);
        String refresh = request.getHeader(REFRESH_TOKEN);
        String requestUri = request.getRequestURI();

        if (StringUtils.hasText(access) && tokenProvider.validateToken(access)) {
            Authentication authentication = tokenProvider.getAuthentication(access);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다. URI : {}", authentication.getName(), requestUri);
        } else if (Objects.nonNull(refresh)) {
            TokenResponse token = authService.reissueToken(refresh);
            response.addHeader(AUTHORIZATION_HEADER, token.getAccess());
            response.addHeader(REFRESH_TOKEN, token.getRefresh());
        } else {
            log.debug("유효한 토큰이 없습니다. URI : {}", requestUri);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveAccess(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
