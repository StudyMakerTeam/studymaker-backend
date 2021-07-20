package com.anytime.studymaker.domain.userStudy;

import com.anytime.studymaker.domain.study.StudyRepository;
import com.anytime.studymaker.domain.status.StatusRepository;
import com.anytime.studymaker.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
  public void deleteAllByStudy(Long studyId) {
    // TODO : 메서드의 목적이 Cascade로 이미 해결될 것 같아용
  }

  @Override
  public void deleteAllByUser(Long userId) {
    // TODO : 메서드의 목적이 Cascade로 이미 해결될 것 같아용2
  }


}
