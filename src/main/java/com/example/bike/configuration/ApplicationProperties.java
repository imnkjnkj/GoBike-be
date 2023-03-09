package com.example.bike.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String googleClientId;
    private Jwt jwt;

    @Getter
    @Setter
    public static class Jwt {
        private String base64secret;
        private long tokenValidityInSeconds;
    }
}


