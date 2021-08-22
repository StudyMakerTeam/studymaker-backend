package com.anytime.studymaker.controller.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
}
