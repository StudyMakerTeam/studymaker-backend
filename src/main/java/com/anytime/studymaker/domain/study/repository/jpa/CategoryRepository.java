package com.anytime.studymaker.domain.study.repository.jpa;

import com.anytime.studymaker.domain.study.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
