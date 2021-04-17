package com.anytime.studymaker.service.study;

import com.anytime.studymaker.domain.study.dto.StudyApiRequest;
import com.anytime.studymaker.domain.study.dto.StudyApiResponse;
import com.anytime.studymaker.service.common.CrudService;

public interface StudyService extends CrudService<StudyApiRequest, StudyApiResponse> {
}
