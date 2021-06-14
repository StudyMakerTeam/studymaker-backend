package com.anytime.studymaker.domain.user.repository.jpa;

import com.anytime.studymaker.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
