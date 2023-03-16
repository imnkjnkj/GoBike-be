package com.example.bike.configuration;

import com.example.bike.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configure the converters to use the ISO format for dates by default.
 */
@Configuration
@RequiredArgsConstructor
public class WebConfiguration {
    private final ApplicationProperties properties;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cors = properties.cors();
        if (!CollectionUtils.isEmpty(cors.getAllowedOrigins()) || !CollectionUtils.isEmpty(cors.getAllowedOriginPatterns())) {
            source.registerCorsConfiguration(Constant.VERSION_1 + "/**", cors);
            source.registerCorsConfiguration("/management/**", cors);
            source.registerCorsConfiguration("/v3/api-docs", cors);
            source.registerCorsConfiguration("/swagger-ui/**", cors);
        }
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
