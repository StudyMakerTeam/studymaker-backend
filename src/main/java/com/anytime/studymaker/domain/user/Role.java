package com.anytime.studymaker.domain.user;

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

    private String role;

    public Role(String role) {
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
