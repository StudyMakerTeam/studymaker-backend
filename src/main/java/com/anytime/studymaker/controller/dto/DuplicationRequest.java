package com.anytime.studymaker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DuplicationRequest {
    private String email;
    private String nickname;
}
