package com.anytime.studymaker.controller.dto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
@Builder
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
}
