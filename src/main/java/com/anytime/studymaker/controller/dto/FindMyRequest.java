package com.anytime.studymaker.controller.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class FindMyRequest {

    private String email;
}
