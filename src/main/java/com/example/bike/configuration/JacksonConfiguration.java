package com.example.bike.configuration;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


@Configuration
public class JacksonConfiguration {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return builder -> builder
                .indentOutput(true)
                .failOnUnknownProperties(false);
    }
}
