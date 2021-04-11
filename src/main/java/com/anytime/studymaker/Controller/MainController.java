package com.anytime.studymaker.Controller;

import java.util.Date;

import com.anytime.studymaker.Controller.common.CrudController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController implements CrudController {

    @GetMapping("/main")
    public String hello() {
        return "안녕하세요. 현재 서버시간은 " + new Date() + "입니다. \n";

    }

    // TODO : 메인

}
