package com.anytime.studymaker.domain.user.dto;

import com.anytime.studymaker.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class UserApiRequest {

    @Resource
    private PasswordEncoder passwordEncoder;

    private String email;
    private String name;
    private String nickname;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public User toEntity() {
        return User.builder().name(name).nickname(nickname).password(passwordEncoder.encode(password)).createAt(LocalDateTime.now()).build();
    }
}
