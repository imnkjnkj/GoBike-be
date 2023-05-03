package com.example.bike;

import com.example.bike.configuration.ApplicationProperties;
import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableJpaAuditing
@EnableJpaRepositories(
        value = "com.example.bike.repository",
        repositoryBaseClass = BaseJpaRepositoryImpl.class
)
public class BikeBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeBeApplication.class, args);
    }

}
