package com.anytime.studymaker.controller.dto;

import lombok.*;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponse {
    private String access;
    private String refresh;
}
