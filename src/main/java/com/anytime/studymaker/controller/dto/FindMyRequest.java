package com.anytime.studymaker.controller.dto;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class FindMyRequest {

    private String email;
}
