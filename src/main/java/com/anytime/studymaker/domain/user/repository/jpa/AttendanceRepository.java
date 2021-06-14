package com.anytime.studymaker.domain.user.repository.jpa;

import com.anytime.studymaker.domain.user.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
