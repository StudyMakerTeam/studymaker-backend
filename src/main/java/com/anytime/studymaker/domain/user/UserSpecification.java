package com.anytime.studymaker.domain.user;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class UserSpecification {
    public static Specification<User> email(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), makeLikePattern(email));
    }

    public static Specification<User> name(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), makeLikePattern(name));
    }

    public static Specification<User> nickname(String nickname) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nickname"), makeLikePattern(nickname));
    }

    public static Specification<User> createdAt(LocalDateTime from, LocalDateTime to) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("createdAt"), from, to);
    }

    private static String makeLikePattern(String input) {
        return "%" + input + "%";
    }
}
