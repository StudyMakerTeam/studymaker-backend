package com.anytime.studymaker.service.study;

import com.anytime.studymaker.domain.study.dto.StudyApiRequest;
import com.anytime.studymaker.domain.study.dto.StudyApiResponse;
import com.anytime.studymaker.service.common.CrudService;
import org.springframework.security.access.annotation.Secured;

public interface StudyService extends CrudService<StudyApiRequest, StudyApiResponse> {

    @Secured("ROLE_USER")
    @Override
    void create(StudyApiRequest studyApiRequest);

    @Secured("ROLE_USER")
    @Override
    StudyApiResponse read(Long id);

    @Secured("ROLE_USER")
    @Override
    StudyApiResponse update(StudyApiRequest studyApiRequest);

    @Secured("ROLE_USER")
    @Override
    void delete(Long id);
}
