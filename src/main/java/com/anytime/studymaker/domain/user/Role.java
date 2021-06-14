package com.anytime.studymaker.domain.user;

import com.anytime.studymaker.domain.user.component.Authority;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString(exclude = "user")
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long roleId;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Role(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority.getRole();
    }
}