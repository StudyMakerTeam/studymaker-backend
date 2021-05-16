package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.user.dto.UserApiRequest;
import com.anytime.studymaker.domain.user.dto.UserApiResponse;
import com.anytime.studymaker.domain.user.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //    UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }

    //    CrudService
    @Override
    public void create(UserApiRequest userApiRequest) {
        userApiRequest.setPassword(passwordEncoder.encode(userApiRequest.getPassword()));
        userRepository.save(userApiRequest.toEntity());
    }

    @Override
    public UserApiResponse read(Long id) {
        return userRepository.findById(id).map(user -> user.toApiResponse()).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }

    @Override
    public UserApiResponse update(UserApiRequest userApiRequest) {
//        todo : 사용자 정보 수정
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}