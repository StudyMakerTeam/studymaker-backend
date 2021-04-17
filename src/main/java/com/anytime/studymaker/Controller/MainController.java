package com.anytime.studymaker.Controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/main")
@RestController
public class MainController{

    // TODO : 메인

    @GetMapping("/main")
    public String hello() {
        return "안녕하세요. 현재 서버시간은 " + new Date() + "입니다. \n";

    }
}
