package com.anytime.studymaker.controller.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class DuplicationRequest {
    private String email;
    private String nickname;
}
