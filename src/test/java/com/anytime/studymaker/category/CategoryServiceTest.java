//package com.anytime.studymaker.category;
//
//import com.anytime.studymaker.controller.dto.CategoryCreateRequest;
//import com.anytime.studymaker.domain.category.Category;
//import com.anytime.studymaker.domain.category.CategoryService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class CategoryServiceTest {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @Test
//    public void create() {
//        CategoryCreateRequest request = CategoryCreateRequest.builder().categoryName("Language").build();
//
//        Category response = categoryService.create(request);
//        Assertions.assertNotNull(response);
//    }
//
//    @Test
//    public void read() {
//
//    }
//}
