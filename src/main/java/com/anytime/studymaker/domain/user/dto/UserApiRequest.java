package com.anytime.studymaker.domain.user.dto;

import com.anytime.studymaker.domain.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class UserApiRequest {

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
