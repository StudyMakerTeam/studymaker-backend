package com.anytime.studymaker.domain.study;

import com.anytime.studymaker.controller.dto.StudyRequest;
import com.anytime.studymaker.controller.dto.StudyResponse;
import org.springframework.security.access.annotation.Secured;

public interface StudyService {

    @Secured("ROLE_USER")
    Study create(StudyRequest studyRequest);

    StudyResponse read(Long id);

    @Secured("ROLE_USER")
    StudyResponse update(StudyRequest studyRequest);

    @Secured("ROLE_USER")
    void delete(Long id);
}
