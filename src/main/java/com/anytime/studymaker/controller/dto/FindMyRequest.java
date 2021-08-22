package com.anytime.studymaker.controller.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class FindMyRequest {

    private String email;
}
