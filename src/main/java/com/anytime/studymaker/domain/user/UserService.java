package com.anytime.studymaker.domain.user;

import com.anytime.studymaker.controller.dto.UserRequest;
import com.anytime.studymaker.controller.dto.UserResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    void create(UserRequest request);

    @Secured("ROLE_USER")
    UserResponse read(Long id);

    @Secured("ROLE_USER")
    UserResponse update(UserRequest request);

    @Secured("ROLE_USER")
    void delete(Long id);

    boolean existEmail(String email);

    boolean existNickname(String nickname);

    void changePassword(UserRequest request);

    Long getUserIdByEmail(String email);
}
