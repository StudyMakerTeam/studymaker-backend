package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.user.Role;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.component.Authority;
import com.anytime.studymaker.domain.user.dto.UserApiRequest;
import com.anytime.studymaker.domain.user.dto.UserApiResponse;
import com.anytime.studymaker.domain.user.repository.jpa.RoleRepository;
import com.anytime.studymaker.domain.user.repository.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //    UserDetailsService
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }

    //    CrudService
    @Override
    public void create(UserApiRequest userApiRequest, PasswordEncoder passwordEncoder) {
        userApiRequest.setPassword(passwordEncoder.encode(userApiRequest.getPassword()));
        User user = userRepository.save(userApiRequest.toEntity());

        Role role = Role.builder().user(user).build();
        role.setAuthority(Authority.ROLE_USER);
        roleRepository.save(role);
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

    @Override
    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
