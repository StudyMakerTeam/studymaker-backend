package com.anytime.studymaker.service.study;

import javax.transaction.Transactional;

import com.anytime.studymaker.domain.study.Study;
import com.anytime.studymaker.domain.study.dto.StudyApiRequest;
import com.anytime.studymaker.domain.study.dto.StudyApiResponse;
import com.anytime.studymaker.domain.study.repository.jpa.StudyRepository;
import com.anytime.studymaker.domain.user.UserStudy;
import com.anytime.studymaker.domain.user.repository.jpa.UserRepository;
import com.anytime.studymaker.domain.user.repository.jpa.UserStudyRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class StudyServiceImpl implements StudyService{

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;

    @Override
    public void create(StudyApiRequest studyApiRequest) {

        Study study = studyRepository.save(studyApiRequest.toEntity());
        UserStudy userStudy = userStudyRepository.save(UserStudy.builder().user(userRepository.getOne(studyApiRequest.getUserId())).study(study).build());
        // userStudy state 추가해야함.
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
