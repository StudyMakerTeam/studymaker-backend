package com.anytime.studymaker.config.jwt;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
