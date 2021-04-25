package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.user.dto.UserApiRequest;
import com.anytime.studymaker.domain.user.dto.UserApiResponse;
import com.anytime.studymaker.service.common.CrudService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, CrudService<UserApiRequest, UserApiResponse> {

    @Secured("ROLE_USER")
    @Override
    void create(UserApiRequest request);

    @Secured("ROLE_USER")
    @Override
    UserApiResponse read(Long id);

    @Secured("ROLE_USER")
    @Override
    UserApiResponse update(UserApiRequest request);

    @Secured("ROLE_USER")
    @Override
    void delete(Long id);
}
