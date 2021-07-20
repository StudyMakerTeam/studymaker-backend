package com.anytime.studymaker.domain.userStudy;

import com.anytime.studymaker.domain.study.Study;
import com.anytime.studymaker.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStudyRepository extends JpaRepository<UserStudy, Long> {
    void deleteAllByUser(User study);
    void deleteAllByStudy(Study study);
}
