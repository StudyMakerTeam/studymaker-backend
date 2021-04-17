package com.anytime.studymaker.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class LoginDto {
    private String email;
    private String name;
}
