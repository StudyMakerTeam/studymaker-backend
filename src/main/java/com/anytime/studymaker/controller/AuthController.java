package com.anytime.studymaker.controller;

import java.util.Date;

import com.anytime.studymaker.auth.AuthService;
import com.anytime.studymaker.controller.dto.*;
import com.anytime.studymaker.domain.user.UserService;
import com.anytime.studymaker.util.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final MailService mailService;

    private final AuthenticationManager manager;


    @GetMapping
    public String hello() {
        return "안녕하세요. 현재 서버시간은 " + new Date() + "입니다. \n";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequest request) {
        userService.create(request);
        return ResponseEntity.ok("회원 가입이 완료되었습니다.");
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> signIn(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.signIn(request, manager);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/check-email")
    public ResponseEntity<DuplicationResponse> checkEmail(@RequestBody DuplicationRequest request) {
        boolean result = userService.existEmail(request.getEmail());
        DuplicationResponse response = DuplicationResponse.builder().result(result).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<DuplicationResponse> checkNickname(@RequestBody DuplicationRequest request) {
        boolean result = userService.existNickname(request.getNickname());
        DuplicationResponse response = DuplicationResponse.builder().result(result).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/find-my-password")
    public ResponseEntity<?> findMyPassword(@RequestBody FindMyRequest request) {
        if (userService.existEmail(request.getEmail())) {
            mailService.sendAuthenticationMail(request.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("입력하신 이메일에 비밀번호 변경 링크를 발송했습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("찾으시는 계정이 존재하지 않습니다.");
        }
    }

    @GetMapping("/initialization/{email}")
    public ResponseEntity<?> getInitializationInfo(@PathVariable String email) {
        Long userId = userService.getUserIdByEmail(email);
        return ResponseEntity.ok().body(userId);
    }

    @PostMapping("/initialization")
    public ResponseEntity<?> initializePassword(@RequestBody UserRequest request) {
        userService.changePassword(request);
        return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@RequestBody TokenRequest request) {
        TokenResponse response = authService.reissueToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }
}
