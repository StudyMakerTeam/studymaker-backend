package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.user.Roles;
import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.component.Authority;
import com.anytime.studymaker.controller.dto.UserApiRequest;
import com.anytime.studymaker.controller.dto.UserApiResponse;
import com.anytime.studymaker.domain.user.repository.RoleRepository;
import com.anytime.studymaker.domain.user.repository.UserRepository;
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

        Roles roles = Roles.builder().user(user).build();
        roles.setAuthority(Authority.ROLE_USER);
        roleRepository.save(roles);
    }

    @Override
    public UserApiResponse read(Long id) {
        return userRepository.findById(id).map(user -> user.toApiResponse()).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }

    @Override
    public UserApiResponse update(UserApiRequest userApiRequest) {
        User updatedUser = userRepository.save(userApiRequest.toEntity());
        return updatedUser.toApiResponse();
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

    @Override
    public void changePassword(UserApiRequest request, PasswordEncoder passwordEncoder) {
        User user = User.builder().userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).map(user -> user.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }
}
