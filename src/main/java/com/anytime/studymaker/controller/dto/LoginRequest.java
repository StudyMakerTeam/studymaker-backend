package com.anytime.studymaker.controller.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotNull
    @Size(min = 3, max = 100)
    private String email;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;
}
