package com.anytime.studymaker.controller.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TokenResponse {
    private String access;
    private String refresh;
}
