package com.anytime.studymaker.exception.user;

import com.anytime.studymaker.controller.UserController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {UserController.class})
public class UserControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException() {
        return ResponseEntity.notFound().build();
    }
}
