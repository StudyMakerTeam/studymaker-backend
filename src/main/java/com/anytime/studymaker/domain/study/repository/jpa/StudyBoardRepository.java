package com.anytime.studymaker.domain.study.repository.jpa;

import com.anytime.studymaker.domain.study.StudyBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyBoardRepository extends JpaRepository<StudyBoard, Long> {
}
