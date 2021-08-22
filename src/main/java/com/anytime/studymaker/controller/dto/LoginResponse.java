package com.anytime.studymaker.controller.dto;

import lombok.*;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {
    private TokenResponse token;
    private Long userId;
    private String email;
    private String name;
    private String nickname;
}
