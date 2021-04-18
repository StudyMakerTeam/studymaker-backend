package com.anytime.studymaker.domain.user;

import com.anytime.studymaker.domain.study.Reply;
import com.anytime.studymaker.domain.study.StudyBoard;
import com.anytime.studymaker.domain.user.dto.UserApiResponse;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@RedisHash("studymaker")
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


    @OneToMany(mappedBy = "user")
    List<UserStudy> userStudyList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<StudyBoard> studyBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Role> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList;
    }

    @Override
    public String getPassword() {
        return this.getPassword();
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

    public UserApiResponse toApiResponse() {
        return UserApiResponse.builder().email(email).name(name).nickname(nickname).createAt(createAt).updateAt(updateAt).build();
    }
}
