package com.anytime.studymaker.service.study;

import com.anytime.studymaker.domain.study.dto.StudyApiRequest;
import com.anytime.studymaker.domain.study.dto.StudyApiResponse;
import org.springframework.stereotype.Service;

@Service
public class StudyServiceImpl implements StudyService{

    @Override
    public void create(StudyApiRequest studyApiRequest) {

    }

    @Override
    public StudyApiResponse read(Long id) {
        return null;
    }

    @Override
    public StudyApiResponse update(StudyApiRequest studyApiRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
