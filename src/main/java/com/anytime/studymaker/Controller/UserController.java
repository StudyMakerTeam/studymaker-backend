package com.anytime.studymaker.Controller;

import com.anytime.studymaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

//    TODO : 회원가입, 정보수정

}
