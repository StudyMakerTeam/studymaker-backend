package com.anytime.studymaker.controller.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class LoginResponse {
    private TokenResponse token;
    private Long userId;
    private String email;
    private String name;
    private String nickname;
}
