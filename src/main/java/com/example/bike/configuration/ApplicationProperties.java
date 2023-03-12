package com.example.bike.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;


@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public record ApplicationProperties(
        String googleClientId,
        @NestedConfigurationProperty Jwt jwt
) {
    public record Jwt(String base64secret, long tokenValidityInSeconds) {}
}




