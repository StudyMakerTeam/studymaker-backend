package com.anytime.studymaker.domain.category;

import com.anytime.studymaker.controller.dto.CategoryCreateRequest;
import com.anytime.studymaker.util.StudymakerMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(CategoryCreateRequest request) {
        Category response = categoryRepository.save(request.toEntity());
        return response;
    }

    @Override
    public Category read(Long categoryId) {
        Category response = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Category.CATEGORY_NOT_FOUND));

        return response;
    }
}
