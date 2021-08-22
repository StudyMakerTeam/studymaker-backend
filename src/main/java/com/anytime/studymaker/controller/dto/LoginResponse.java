package com.anytime.studymaker.controller.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class LoginResponse {
    private TokenResponse token;
    private Long userId;
    private String email;
    private String name;
    private String nickname;
}
