package com.anytime.studymaker.domain.user.cache;

import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<Token, String> {
}
