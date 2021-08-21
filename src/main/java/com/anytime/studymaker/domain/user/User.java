package com.anytime.studymaker.domain.user;

import com.anytime.studymaker.domain.role.Roles;
import com.anytime.studymaker.domain.studyBoard.Reply;
import com.anytime.studymaker.domain.studyBoard.StudyBoard;
import com.anytime.studymaker.controller.dto.UserResponse;
import com.anytime.studymaker.domain.userStudy.UserStudy;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@Accessors(chain = true)
@ToString(exclude = "rolesList")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long userId;
    private String email;
    private String name;
    private String nickname;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;


    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<UserStudy> userStudyList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<StudyBoard> studyBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Roles> rolesList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.rolesList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserResponse toApiResponse() {
        return UserResponse.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .createAt(createAt)
                .updateAt(updateAt)
                .build();
    }
}
