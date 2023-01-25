package com.locap.locadora.config;

import com.locap.locadora.services.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@RequiredArgsConstructor
@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    @Bean
    public void instanciaDb() {
        this.dbService.instanciaDB();
    }
}