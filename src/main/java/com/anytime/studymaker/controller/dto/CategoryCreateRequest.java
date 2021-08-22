package com.anytime.studymaker.controller.dto;

import com.anytime.studymaker.domain.category.Category;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Builder
public class CategoryCreateRequest {
    private String categoryName;

    public Category toEntity() {
        return Category.builder().categoryName(this.categoryName).build();
    }
}
