package com.anytime.studymaker.service.study;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.anytime.studymaker.domain.study.Study;
import com.anytime.studymaker.domain.study.dto.StudyApiRequest;
import com.anytime.studymaker.domain.study.dto.StudyApiResponse;
import com.anytime.studymaker.domain.study.repository.jpa.CategoryRepository;
import com.anytime.studymaker.domain.study.repository.jpa.RegionRepository;
import com.anytime.studymaker.domain.study.repository.jpa.StudyRepository;
import com.anytime.studymaker.domain.user.Status;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.UserStudy;
import com.anytime.studymaker.domain.user.repository.jpa.UserStudyRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class StudyServiceImpl implements StudyService {

    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;
    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;

    @Override
    public void create(StudyApiRequest studyApiRequest) {
        User user = User.builder().userId(studyApiRequest.getUserId()).build();

        // statusId 1: 방장, 2: 부방장, 3: 일반 회원, 4: 추방된 회원
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

        Optional<Study> Ostudy = studyRepository.findById(studyApiRequest.getStudyId());
        if (Ostudy.isPresent()) {
            Study study = Ostudy.get();
            if (StringUtils.isNotBlank(studyApiRequest.getStudyName())) {
                study.setStudyName(studyApiRequest.getStudyName());
            }
            if (StringUtils.isNotBlank(Integer.toString(studyApiRequest.getStudyMaximum()))) {
                study.setStudyMaximum(studyApiRequest.getStudyMaximum());
            }
            if (StringUtils.isNotBlank(studyApiRequest.getStudySummary())) {
                study.setStudySummary(studyApiRequest.getStudySummary());
            }
            if (StringUtils.isNotBlank(studyApiRequest.getStudyDescription())) {
                study.setStudyDescription(studyApiRequest.getStudyDescription());
            }
            if (StringUtils.isNotBlank(studyApiRequest.getStudyImage())) {
                study.setStudyImage(studyApiRequest.getStudyImage());
            }
            if (StringUtils.isNotBlank(Boolean.toString(studyApiRequest.getStudyStatus()))) {
                study.setStudyStatus(studyApiRequest.getStudyStatus());
            }
            if (StringUtils.isNotBlank(Boolean.toString(studyApiRequest.getStudyType()))) {
                study.setStudyType(studyApiRequest.getStudyType());
            }
            if (categoryRepository.findById(studyApiRequest.getCategoryId()).isPresent()) {
                study.setCategory(categoryRepository.getOne(studyApiRequest.getCategoryId()));
            }
            if (regionRepository.findById(studyApiRequest.getStudyId()).isPresent()) {
                study.setRegion(regionRepository.getOne(studyApiRequest.getRegionId()));
            }
            studyRepository.save(study);
            return study.toApiResponse();
        }

        throw new EntityNotFoundException("존재하지 않는 스터디입니다.");

    }

    
    @Override
    public void delete(Long id) {
        
        // 유저스터디 삭제
        
        
        studyRepository.deleteById(id);
    }
}
