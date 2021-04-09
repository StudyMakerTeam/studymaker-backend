package com.anytime.studymaker.domain.user;

import lombok.Getter;

@Getter
public enum Authority {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
    private String name;

    Authority(String name) {
        this.name = name;
    }
}
