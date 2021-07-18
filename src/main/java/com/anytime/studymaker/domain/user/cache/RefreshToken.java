package com.anytime.studymaker.domain.user.cache;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Builder
@RequiredArgsConstructor
@RedisHash(value = "refresh-token", timeToLive = 60 * 60 * 24 * 7L)
public class RefreshToken {

    @Id
    private final String refreshToken;
    private final String accessToken;
    private final Long userId;
}
