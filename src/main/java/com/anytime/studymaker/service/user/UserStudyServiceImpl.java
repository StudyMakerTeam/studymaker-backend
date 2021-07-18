package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.study.repository.StudyRepository;
import com.anytime.studymaker.domain.user.UserStudy;
import com.anytime.studymaker.domain.user.repository.StatusRepository;
import com.anytime.studymaker.domain.user.repository.UserRepository;
import com.anytime.studymaker.domain.user.repository.UserStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserStudyServiceImpl implements UserStudyService {

  private final UserRepository userRepository;
  private final StudyRepository studyRepository;
  private final StatusRepository statusRepository;
  private final UserStudyRepository userStudyRepository;

  @Override
  public void create(Long userId, Long studyId, Long statusId) {

    UserStudy userStudy = UserStudy.builder().user(userRepository.getOne(userId)).study(studyRepository.getOne(studyId))
        .status(statusRepository.getOne(statusId)).build();
    
    userStudyRepository.save(userStudy);
  }

  @Override
  public void deleteByStudy(Long studyId) {

    
    
  }

  @Override
  public void deleteByUser(Long userId) {
    // TODO Auto-generated method stub
    
  }


}
