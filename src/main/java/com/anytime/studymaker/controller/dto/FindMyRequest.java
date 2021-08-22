package com.anytime.studymaker.controller.dto;

import lombok.*;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FindMyRequest {

    private String email;
}
