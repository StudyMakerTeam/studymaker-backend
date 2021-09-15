//package com.anytime.studymaker.study;
//
//import com.anytime.studymaker.controller.dto.StudyRequest;
//import com.anytime.studymaker.domain.category.Category;
//import com.anytime.studymaker.domain.category.CategoryService;
//import com.anytime.studymaker.domain.study.StudyService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import java.time.LocalDateTime;
//
//@SpringBootTest
//public class StudyServiceTest {
//
//    @Autowired
//    private StudyService studyService;
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @WithMockUser(username = "jeongyong")
//    @Test
//    public void saveStudy() {
//        StudyRequest request = StudyRequest.builder()
//                .studyStatus(true).studyName("스터디 생성 테스트").studyType(true)
//                .categoryId(13L).createAt(LocalDateTime.now()).studySummary("서머리")
//                .userId(1L).build();
//
//        Assertions.assertNotNull(studyService.create(request));
//    }
//}
