package com.anytime.studymaker.service.user;

import com.anytime.studymaker.domain.user.dto.UserApiRequest;
import com.anytime.studymaker.domain.user.dto.UserApiResponse;
import com.anytime.studymaker.service.common.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService, CrudService<UserApiRequest, UserApiResponse> {

}
