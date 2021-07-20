package com.anytime.studymaker.domain.status;

import com.anytime.studymaker.domain.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
