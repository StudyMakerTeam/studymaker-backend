package com.anytime.studymaker.controller.dto;

import com.anytime.studymaker.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CategoryCreateRequest {
    private String categoryName;

    public Category toEntity() {
        return Category.builder().categoryName(this.categoryName).build();
    }
}
