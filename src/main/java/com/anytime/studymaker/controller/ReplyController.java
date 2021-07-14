package com.anytime.studymaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/reply")
@RestController
public class ReplyController {

    @PostMapping
    public ResponseEntity<?> saveReply() {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getReply() {
        return ResponseEntity.ok().build();
    }
}
