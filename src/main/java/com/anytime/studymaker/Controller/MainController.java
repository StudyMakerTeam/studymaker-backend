package com.anytime.studymaker.Controller;

import java.util.Date;

import com.anytime.studymaker.config.jwt.Token;
import com.anytime.studymaker.config.jwt.JWTProvider;
import com.anytime.studymaker.domain.common.Header;
import com.anytime.studymaker.domain.user.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MainController{

    private final JWTProvider JWTProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/main")
    public String hello() {
        return "안녕하세요. 현재 서버시간은 " + new Date() + "입니다. \n";
    }

    @PostMapping("/signin")
    public Header<Token> signIn(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JWTProvider.createToken(authentication);
        Token token = new Token(jwt);
        return Header.ok(token);
    }
}
