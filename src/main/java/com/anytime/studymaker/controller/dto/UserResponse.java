package com.anytime.studymaker.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class UserResponse {
    private String email;
    private String name;
    private String nickname;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}