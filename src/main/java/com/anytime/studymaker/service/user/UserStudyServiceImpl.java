package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.study.repository.jpa.StudyRepository;
import com.anytime.studymaker.domain.user.UserStudy;
import com.anytime.studymaker.domain.user.repository.jpa.UserRepository;
import com.anytime.studymaker.domain.user.repository.jpa.UserStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserStudyServiceImpl implements UserStudyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final UserStudyRepository userStudyRepository;
    

    @Override
    public void create(Long userId, Long studyId) {

      //  UserStudy userStudy = UserStudy.builder().user(userRepository.getOne(userId)).study(studyRepository.getOne(studyId)).build();
        
                
    }
    
}
