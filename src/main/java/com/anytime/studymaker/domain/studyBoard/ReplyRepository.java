package com.anytime.studymaker.domain.studyBoard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReplyRepository extends JpaRepository<Reply, Long>, JpaSpecificationExecutor<Reply> {
}
