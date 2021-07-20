package com.anytime.studymaker.domain.userStudy;

import org.springframework.security.access.annotation.Secured;

public interface UserStudyService {

    @Secured("ROLE_USER")
    void create(Long userId, Long studyId, Long statusId);

    @Secured("ROLE_USER")
    void deleteAllByStudy(Long studyId);

    @Secured("ROLE_USER")
    void deleteAllByUser(Long userId);
    
}
