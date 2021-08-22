package com.anytime.studymaker.domain.user;

import com.anytime.studymaker.controller.dto.UserSearchRequest;
import com.anytime.studymaker.domain.role.Authority;
import com.anytime.studymaker.controller.dto.UserRequest;
import com.anytime.studymaker.controller.dto.UserResponse;
import com.anytime.studymaker.domain.role.RoleRepository;
import com.anytime.studymaker.domain.role.Roles;
import com.anytime.studymaker.util.StudymakerMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다."));
    }

    @Override
    public void create(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = userRepository.save(userRequest.toEntity());

        Roles roles = Roles.builder().user(user).build();
        roles.setAuthority(Authority.ROLE_USER);
        roleRepository.save(roles);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(StudymakerMessages.User.USER_NOT_FOUND));
    }

    @Override
    public UserResponse read(Long id) {
        return userRepository.findById(id).map(User::toApiResponse)
                .orElseThrow(() -> new UsernameNotFoundException(StudymakerMessages.User.USER_NOT_FOUND));
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        User updatedUser = userRepository.save(userRequest.toEntity());
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
    public void changePassword(UserRequest request) {
        User user = User.builder().userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).map(User::getUserId)
                .orElseThrow(() -> new UsernameNotFoundException(StudymakerMessages.User.USER_NOT_FOUND));
    }

    @Override
    public List<UserResponse> searchUser(UserSearchRequest request) {
        Specification<User> specification = UserSpecification.name(request.getName())
                .and(UserSpecification.email(request.getEmail()))
                .and(UserSpecification.nickname(request.getNickname()))
                .and(UserSpecification.createdAt(request.getFrom(), request.getTo()));

        List<UserResponse> result = userRepository.findAll(specification).stream()
                .map(User::toApiResponse).collect(Collectors.toList());
        return result;
    }
}
