package com.anytime.studymaker.domain.role;

import com.anytime.studymaker.domain.user.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Builder
@ToString(exclude = "user")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Roles(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority.getRole();
    }
}