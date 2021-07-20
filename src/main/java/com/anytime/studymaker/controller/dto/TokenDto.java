package com.anytime.studymaker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenDto {
    private String access;
    private String refresh;
}
