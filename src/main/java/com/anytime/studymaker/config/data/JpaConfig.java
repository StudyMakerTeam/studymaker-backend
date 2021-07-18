package com.anytime.studymaker.config.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.anytime.studymaker.domain.**.repository")
public class JpaConfig {
}
