package com.anytime.studymaker.controller.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserSearchRequest {
    private String email;
    private String name;
    private String nickname;
    private LocalDateTime from;
    private LocalDateTime to;
}
