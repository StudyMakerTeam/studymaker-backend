package com.anytime.studymaker.auth.jwt;

import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ClassUtils;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        User user = userService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        } else if (!user.isEnabled()) {
            throw new BadCredentialsException("이용이 중지된 계정입니다.");
        }

        return new UsernamePasswordAuthenticationToken(user, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ClassUtils.isAssignable(Authentication.class, authentication);
    }
}
