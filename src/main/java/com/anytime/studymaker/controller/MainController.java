package com.anytime.studymaker.controller;

import java.util.Date;

import com.anytime.studymaker.config.jwt.Token;
import com.anytime.studymaker.config.jwt.JWTProvider;
import com.anytime.studymaker.domain.user.dto.LoginDto;
import com.anytime.studymaker.domain.user.dto.UserApiRequest;
import com.anytime.studymaker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    private final UserService userService;
    private final JWTProvider JWTProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/main")
    public String hello() {
        return "안녕하세요. 현재 서버시간은 " + new Date() + "입니다. \n";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserApiRequest request) {
        //    TODO : 회원가입
        userService.create(request);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("회원 가입이 완료되었습니다.");
        return responseEntity;
    }

    @PostMapping("/signin")
    public ResponseEntity<Token> signIn(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JWTProvider.createToken(authentication);
        Token token = new Token(jwt);
        return ResponseEntity.ok(token);
    }
}
