package com.anytime.studymaker.domain.role;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private String role;

    Authority(String role) {
        this.role = role;
    }
}
