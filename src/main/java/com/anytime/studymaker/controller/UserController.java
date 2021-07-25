package com.anytime.studymaker.controller;

import com.anytime.studymaker.controller.dto.UserRequest;
import com.anytime.studymaker.controller.dto.UserResponse;
import com.anytime.studymaker.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserResponse response = userService.read(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMe(UserRequest request) {
        return ResponseEntity.ok().build();
    }
}