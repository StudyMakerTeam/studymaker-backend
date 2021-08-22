package com.anytime.studymaker.controller.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class DuplicationRequest {
    private String email;
    private String nickname;
}
