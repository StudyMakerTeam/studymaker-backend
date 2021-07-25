package com.anytime.studymaker.domain.study;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.anytime.studymaker.controller.dto.StudyRequest;
import com.anytime.studymaker.controller.dto.StudyResponse;
import com.anytime.studymaker.domain.category.CategoryRepository;
import com.anytime.studymaker.domain.status.Status;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.userStudy.UserStudy;
import com.anytime.studymaker.domain.userStudy.UserStudyRepository;

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
    public void create(StudyRequest studyRequest) {
        User user = User.builder().userId(studyRequest.getUserId()).build();

        // statusId 1: 방장, 2: 부방장, 3: 일반 회원, 4: 추방된 회원
        Status status = Status.builder().statusId(0l).build();

        Study study = studyRepository.save(studyRequest.toEntity());
        UserStudy userStudy = UserStudy.builder().user(user).study(study).status(status).build();
        userStudyRepository.save(userStudy);
    }

    @Override
    public StudyResponse read(Long id) {
        return studyRepository.findById(id).map(study -> study.toApiResponse())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 스터디입니다."));
    }

    @Override
    public StudyResponse update(StudyRequest studyRequest) {

        Optional<Study> Ostudy = studyRepository.findById(studyRequest.getStudyId());
        if (Ostudy.isPresent()) {
            Study study = Ostudy.get();
            if (StringUtils.isNotBlank(studyRequest.getStudyName())) {
                study.setStudyName(studyRequest.getStudyName());
            }
            if (StringUtils.isNotBlank(Integer.toString(studyRequest.getStudyMaximum()))) {
                study.setStudyMaximum(studyRequest.getStudyMaximum());
            }
            if (StringUtils.isNotBlank(studyRequest.getStudySummary())) {
                study.setStudySummary(studyRequest.getStudySummary());
            }
            if (StringUtils.isNotBlank(studyRequest.getStudyDescription())) {
                study.setStudyDescription(studyRequest.getStudyDescription());
            }
            if (StringUtils.isNotBlank(studyRequest.getStudyImage())) {
                study.setStudyImage(studyRequest.getStudyImage());
            }
            if (StringUtils.isNotBlank(Boolean.toString(studyRequest.getStudyStatus()))) {
                study.setStudyStatus(studyRequest.getStudyStatus());
            }
            if (StringUtils.isNotBlank(Boolean.toString(studyRequest.getStudyType()))) {
                study.setStudyType(studyRequest.getStudyType());
            }
            if (categoryRepository.findById(studyRequest.getCategoryId()).isPresent()) {
                study.setCategory(categoryRepository.getOne(studyRequest.getCategoryId()));
            }
            if (regionRepository.findById(studyRequest.getStudyId()).isPresent()) {
                study.setRegion(regionRepository.getOne(studyRequest.getRegionId()));
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
