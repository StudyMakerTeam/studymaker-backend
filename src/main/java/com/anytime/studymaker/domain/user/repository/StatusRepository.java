package com.anytime.studymaker.domain.user.repository;

import com.anytime.studymaker.domain.user.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
