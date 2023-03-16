package com.example.bike.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.web.cors.CorsConfiguration;


@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public record ApplicationProperties(
        String googleClientId,
        @NestedConfigurationProperty Jwt jwt,
        @NestedConfigurationProperty Amazon amazon,
        @NestedConfigurationProperty CorsConfiguration cors
) {

    public record Jwt(String base64secret, long tokenValidityInSeconds) {}

    public record Amazon(String accessKey, String secretKey, String bucketName,
                         String region, String bucketUrl, String userPoolId, String clientId, String clientSecret){}
}




