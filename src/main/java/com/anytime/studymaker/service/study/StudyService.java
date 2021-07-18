package com.anytime.studymaker.service.study;

import com.anytime.studymaker.controller.dto.StudyApiRequest;
import com.anytime.studymaker.controller.dto.StudyApiResponse;
import org.springframework.security.access.annotation.Secured;

public interface StudyService {

    @Secured("ROLE_USER")
    void create(StudyApiRequest studyApiRequest);

    StudyApiResponse read(Long id);

    @Secured("ROLE_USER")
    StudyApiResponse update(StudyApiRequest studyApiRequest);

    @Secured("ROLE_USER")
    void delete(Long id);
}
