package com.anytime.studymaker.controller;

import com.anytime.studymaker.config.annotation.QueryStringToJson;
import com.anytime.studymaker.controller.dto.UserRequest;
import com.anytime.studymaker.controller.dto.UserResponse;
import com.anytime.studymaker.controller.dto.UserSearchRequest;
import com.anytime.studymaker.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        UserResponse response = userService.read(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> searchUser(@QueryStringToJson UserSearchRequest request) {
        List<UserResponse> response = userService.searchUser(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateMe(@PathVariable Long userId, UserRequest request) {
        UserResponse response = UserResponse.builder().build();
        return ResponseEntity.ok(response);
    }
}