package com.anytime.studymaker.domain.category;

import com.anytime.studymaker.controller.dto.CategoryCreateRequest;

public interface CategoryService {

    public Category create(CategoryCreateRequest request);
    public Category read(Long categoryId);
}
