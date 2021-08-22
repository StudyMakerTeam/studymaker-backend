package com.anytime.studymaker.controller.dto;

import com.anytime.studymaker.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {
    private Long userId;
    private String email;
    private String name;
    private String nickname;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public User toEntity() {
        return User.builder().email(email).password(password).name(name).nickname(nickname).createAt(LocalDateTime.now()).build();
    }
}
