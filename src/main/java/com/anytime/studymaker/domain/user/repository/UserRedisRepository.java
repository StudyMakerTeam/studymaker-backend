package com.anytime.studymaker.domain.user.repository;

import com.anytime.studymaker.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<User, Long> {
}
