package com.anytime.studymaker.domain.user.repository;

import com.anytime.studymaker.domain.user.UserStudy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudy, Long> {
}
