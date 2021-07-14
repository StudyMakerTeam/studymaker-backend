package com.anytime.studymaker.exception.study;

import com.anytime.studymaker.controller.StudyController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {StudyController.class})
public class StudyControllerAdvice {

    @ExceptionHandler(StudyNotFoundException.class)
    public ResponseEntity<?> handleStudyNotFoundException() {
        return ResponseEntity.notFound().build();
    }
}
