package com.anytime.studymaker.domain.study;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.anytime.studymaker.controller.dto.StudyRequest;
import com.anytime.studymaker.controller.dto.StudyResponse;
import com.anytime.studymaker.domain.category.Category;
import com.anytime.studymaker.domain.category.CategoryRepository;
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

    @Override
    public Study create(StudyRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Category.CATEGORY_NOT_FOUND));

        Study study = request.toEntity(category);
        User user = User.builder().userId(request.getUserId()).build();
        UserStudy userStudy = UserStudy.builder().user(user).study(study).userStatus(UserStatus.MANAGER).build();
        study.getUserStudyList().add(userStudy);

        Study response = studyRepository.save(study);
        return response;
    }

    @Override
    public StudyResponse read(Long id) {
        return studyRepository.findById(id).map(Study::toApiResponse)
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Study.STUDY_NOT_FOUND));
    }

    @Override
    public StudyResponse update(StudyRequest request) {
        Study study = studyRepository.findById(request.getStudyId())
                .orElseThrow(() -> new EntityNotFoundException(StudymakerMessages.Study.STUDY_NOT_FOUND));
        study.update(request);
        Study response = studyRepository.save(study);

        return response.toApiResponse();
    }

    @Override
    public void delete(Long id) {
        studyRepository.deleteById(id);
    }
}
