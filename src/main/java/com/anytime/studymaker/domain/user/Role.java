package com.anytime.studymaker.domain.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long roleId;

    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
