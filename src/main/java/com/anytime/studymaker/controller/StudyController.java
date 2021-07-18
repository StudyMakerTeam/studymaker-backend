package com.anytime.studymaker.controller;

import com.anytime.studymaker.controller.dto.StudyApiRequest;
import com.anytime.studymaker.controller.dto.StudyApiResponse;
import com.anytime.studymaker.service.study.StudyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/study")
@RestController
public class StudyController {

    public final StudyService studyService;

    @PostMapping("/create")
    public ResponseEntity<String> createStudy(@RequestBody StudyApiRequest request) {

        studyService.create(request);
        ResponseEntity<String> responseEntity = ResponseEntity.ok("스터디가 생성되었습니다.");

        return responseEntity;
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateStudy(@RequestBody StudyApiRequest request){

        studyService.update(request);

        ResponseEntity<String> responseEntity = ResponseEntity.ok("스터디가 수정되었습니다.");
        return responseEntity;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudy(@RequestBody StudyApiRequest request){

        

        // 출석부 삭제 -> 게시판 삭제 -> 유저스터디 삭제 -> 스터디 삭제
        // => 스터디를 삭제하면, 유저 스터디도 삭제
        // => 유저 스터디를 삭제하면 -> 출석부/ 게시글(판) 삭제

        
     
        studyService.delete(request.getStudyId());

        ResponseEntity<String> responseEntity = ResponseEntity.ok("스터디가 삭제되었습니다.");
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudy(@PathVariable long id) {
        StudyApiResponse response = studyService.read(id);
        return ResponseEntity.ok(response);
    }
}
