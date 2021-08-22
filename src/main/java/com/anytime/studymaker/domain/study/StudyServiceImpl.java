package com.anytime.studymaker.domain.study;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.anytime.studymaker.controller.dto.StudyRequest;
import com.anytime.studymaker.controller.dto.StudyResponse;
import com.anytime.studymaker.domain.category.Category;
import com.anytime.studymaker.domain.category.CategoryRepository;
import com.anytime.studymaker.domain.user.UserService;
import com.anytime.studymaker.domain.userStudy.UserStatus;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.userStudy.UserStudy;

import com.anytime.studymaker.util.StudymakerMessages;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class StudyServiceImpl implements StudyService {
    private final StudyRepository studyRepository;
    private final CategoryRepository categoryRepository;
    private final RegionRepository regionRepository;
    private final UserService userService;

    @Override
    public StudyResponse create(StudyRequest request) {
        User user = userService.getUser(request.getUserId());
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Category.CATEGORY_NOT_FOUND));
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Region.REGION_NOT_FOUND));

        Study study = request.toEntity();
        study.setCategory(category);
        study.setRegion(region);

        UserStudy userStudy = UserStudy.builder().user(user).study(study).userStatus(UserStatus.MANAGER).build();
        study.getUserStudyList().add(userStudy);

        Study saved = studyRepository.save(study);
        StudyResponse response = saved.toApiResponse();
        return response;
    }

    @Override
    public Study read(Long id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Study.STUDY_NOT_FOUND));
    }

    @Override
    public Study update(StudyRequest request) {
        Study study = studyRepository.findById(request.getStudyId())
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Study.STUDY_NOT_FOUND));
        study.update(request);
        return studyRepository.save(study);
    }

    @Override
    public void delete(Long id) {
        studyRepository.deleteById(id);
    }
}
