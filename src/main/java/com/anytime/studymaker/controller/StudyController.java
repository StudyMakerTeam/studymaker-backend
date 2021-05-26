package com.anytime.studymaker.controller;

import com.anytime.studymaker.domain.study.dto.StudyApiRequest;
import com.anytime.studymaker.service.study.StudyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/study")
@RestController
public class StudyController {

    public StudyService studyService;

    @PostMapping("/create")
    public ResponseEntity<String> createStudy(@RequestBody StudyApiRequest request) {

        studyService.create(request);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("스터디가 생성되었습니다.");

        return responseEntity;
    }

}
