package com.anytime.studymaker.service.user;

import org.springframework.security.access.annotation.Secured;

public interface UserStudyService {

    @Secured("ROLE_USER")
    void create(Long userId, Long studyId);

    
    
}
