package com.anytime.studymaker.controller;

import com.anytime.studymaker.controller.dto.ReplyRequest;
import com.anytime.studymaker.controller.dto.ReplyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/api/reply")
@RestController
public class ReplyController {

    @PostMapping
    public ResponseEntity<ReplyResponse> saveReply(@RequestBody ReplyRequest request) {
        ReplyResponse response = ReplyResponse.builder().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{replyId}")
    public ResponseEntity<ReplyResponse> getReply(@PathVariable Long replyId) {
        ReplyResponse response = ReplyResponse.builder().build();
        return ResponseEntity.ok(response);
    }
}
