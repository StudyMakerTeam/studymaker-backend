package com.anytime.studymaker.cache;

import com.anytime.studymaker.domain.user.cache.Token;
import com.anytime.studymaker.domain.user.cache.TokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataRedisTest
public class RedisRepositoryTest {

    @Autowired
    private TokenRepository refreshTokenRepository;

    @Test
    public void saveTokenTest() {

        Token token = new Token("refresh-0001", "access-0001", 1L);
        Token savedToken = refreshTokenRepository.save(token);
        assertEquals(token, savedToken);
    }

    @Test
    public void deleteTokenTest() {
        String target = "refresh-0001";
        refreshTokenRepository.deleteById(target);
        assertNull(refreshTokenRepository.findById(target).orElse(null));
    }
}
