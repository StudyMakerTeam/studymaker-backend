package com.anytime.studymaker.domain.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "rolesList")
    Optional<User> findOneWithRoleListByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

}
