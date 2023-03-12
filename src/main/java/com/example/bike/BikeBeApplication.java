package com.example.bike;

import com.example.bike.configuration.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableJpaAuditing
public class BikeBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeBeApplication.class, args);
    }

}
