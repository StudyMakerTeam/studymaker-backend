package com.anytime.studymaker.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private String email;
    private String name;
    private String nickname;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}