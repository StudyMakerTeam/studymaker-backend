package com.anytime.studymaker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginResponse {
    private TokenDto token;
    private Long userId;
    private String email;
    private String name;
    private String nickname;
}
