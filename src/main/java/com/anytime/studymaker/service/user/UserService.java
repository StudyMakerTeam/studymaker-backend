package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.user.User;
import com.anytime.studymaker.domain.user.dto.UserApiRequest;
import com.anytime.studymaker.domain.user.dto.UserApiResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService extends UserDetailsService {
    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

    void create(UserApiRequest request, PasswordEncoder passwordEncoder);

    @Secured("ROLE_USER")
    UserApiResponse read(Long id);

    @Secured("ROLE_USER")
    UserApiResponse update(UserApiRequest request);

    @Secured("ROLE_USER")
    void delete(Long id);

    boolean existEmail(String email);

    boolean existNickname(String nickname);

    void changePassword(UserApiRequest reques, PasswordEncoder passwordEncoder);

    Long getUserIdByEmail(String email);
}
