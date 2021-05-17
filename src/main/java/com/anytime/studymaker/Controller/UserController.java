package com.anytime.studymaker.Controller;

import com.anytime.studymaker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController
public class UserController {

    private final UserService userService;

<<<<<<< Updated upstream
//    TODO : 회원가입, 정보수정
=======
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/signup")
    public Response singUpUser(@RequestBody UserApiRequest user) {
        try {
            userService.create(user);
            return new Response("success ","회원가입 완료",null);
        } catch (Exception e) {
            return new Response("error", "회원가입 실패", null);
        }
    }

    @PostMapping("login")
    public Response login(@RequestBody UserApiRequest user, HttpServletRequest request, HttpServletResponse response) {
                        
        try {
            final UserApiResponse loginUser = userService.loginUser(user.getEmail(), user.getPassword());
            final String token = jwtUtil.generateToken(loginUser);
            final String refresh = jwtUtil.generateRefreshToken(loginUser);

            Cookie accessToken = cookieUtil.creatCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.creatCookie(JwtUtil.REFRESH_TOKEN_NAME, refresh);

            response.addCookie(accessToken);

            return new Response("success", "로그인 성공", token);

        } catch (Exception e) {
            return new Response("error","로그인 실패", e.getMessage());
        }
    }
    
    // public Response login(@RequestBody RequestLoginUser user, HttpServletRequest
    // request, HttpServletResponse response){

    // }

    // TODO : 회원가입, 정보수정
>>>>>>> Stashed changes

}
