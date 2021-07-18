package com.anytime.studymaker.domain.user.repository;

import com.anytime.studymaker.domain.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "rolesList")
    Optional<User> findOneWithRoleListByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

}
