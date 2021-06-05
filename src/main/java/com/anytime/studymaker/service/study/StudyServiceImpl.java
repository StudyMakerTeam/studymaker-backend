package com.anytime.studymaker.service.study;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.anytime.studymaker.domain.study.Study;
import com.anytime.studymaker.domain.study.dto.StudyApiRequest;
import com.anytime.studymaker.domain.study.dto.StudyApiResponse;
import com.anytime.studymaker.domain.study.repository.jpa.StudyRepository;
import com.anytime.studymaker.domain.user.Status;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.UserStudy;
import com.anytime.studymaker.domain.user.repository.jpa.UserStudyRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class StudyServiceImpl implements StudyService {

    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;

    @Override
    public void create(StudyApiRequest studyApiRequest) {
        User user  = User.builder().userId(studyApiRequest.getUserId()).build();

        //  statusId 0: 방장, 1: 부방장, 2: 일반 회원, 3: 추방된 회원
        Status status = Status.builder().statusId(0l).build();

        Study study = studyRepository.save(studyApiRequest.toEntity());
        UserStudy userStudy = UserStudy.builder().user(user).study(study).status(status).build();
        userStudyRepository.save(userStudy);
    }

    @Override
    public StudyApiResponse read(Long id) {
        return studyRepository.findById(id).map(study -> study.toApiResponse())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 스터디입니다."));
    }

    @Override
    public StudyApiResponse update(StudyApiRequest studyApiRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
