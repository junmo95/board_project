package com.junmo.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("uno"); //TODO: 스프링 시큐리티로 인증 시능을 붙이게 될 때, 수정하자 - 현제 인증기능 부여 안해서, junmo로 다 들어감
    }

}
