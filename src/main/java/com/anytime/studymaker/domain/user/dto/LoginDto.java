package com.anytime.studymaker.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 100)
    private String email;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;
}
