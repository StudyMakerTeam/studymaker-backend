package com.anytime.studymaker.Controller;

import com.anytime.studymaker.domain.common.Header;
import com.anytime.studymaker.domain.user.dto.UserApiRequest;
import com.anytime.studymaker.domain.user.dto.UserApiResponse;
import com.anytime.studymaker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public Header<UserApiResponse> signUp(UserApiRequest request) {
        //    TODO : 회원가입

        return new Header<UserApiResponse>();
    }
}
