package com.anytime.studymaker.controller;

import com.anytime.studymaker.controller.dto.StudyRequest;
import com.anytime.studymaker.controller.dto.StudyResponse;
import com.anytime.studymaker.domain.study.Study;
import com.anytime.studymaker.domain.study.StudyService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/study")
@RestController
public class StudyController {

    public final StudyService studyService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudy(@PathVariable long id) {
        Study study = studyService.read(id);
        StudyResponse response = study.toApiResponse();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<StudyResponse> createStudy(@RequestBody StudyRequest request) {
        StudyResponse response = studyService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<StudyResponse> updateStudy(@RequestBody StudyRequest request) {
        Study study = studyService.update(request);
        StudyResponse response = study.toApiResponse();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudy(@RequestBody StudyRequest request) {
        // 출석부 삭제 -> 게시판 삭제 -> 유저스터디 삭제 -> 스터디 삭제
        // => 스터디를 삭제하면, 유저 스터디도 삭제
        // => 유저 스터디를 삭제하면 -> 출석부/ 게시글(판) 삭제
        studyService.delete(request.getStudyId());

        ResponseEntity<String> responseEntity = ResponseEntity.ok("스터디가 삭제되었습니다.");
        return responseEntity;
    }
}
