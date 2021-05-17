package com.anytime.studymaker.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
