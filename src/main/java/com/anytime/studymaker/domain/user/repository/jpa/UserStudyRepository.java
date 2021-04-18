package com.anytime.studymaker.domain.user.repository.jpa;

import com.anytime.studymaker.domain.user.UserStudy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudy, Long> {
}
