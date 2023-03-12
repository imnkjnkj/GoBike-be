package com.example.bike.configuration;

import com.example.bike.security.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.bike.security.jwt.JWTConfigurer;
import com.example.bike.security.jwt.TokenProvider;
import com.example.bike.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .cors()
            .and()
            .csrf().disable()
            .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint())
            .and()
                .headers()
                    .contentSecurityPolicy("default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:")
                .and()
                    .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                .and()
                    .frameOptions().sameOrigin()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("oauth2/**").permitAll()
                .requestMatchers(Constant.VERSION_1+"/user/authenticate").permitAll()
                .requestMatchers(Constant.VERSION_1+"/user/**").authenticated()
                .requestMatchers(Constant.VERSION_1+"/news/**").authenticated()
                .requestMatchers(Constant.VERSION_1+"/category/**").authenticated()
            .and()
                .httpBasic()
            .and()
                .oauth2Client()
            .and()
                .apply(jwtConfigurerAdapter());
        return http.build();
        // @formatter:on
    }

    private JWTConfigurer jwtConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) ->
                response.sendError(HttpStatus.FORBIDDEN.value(), accessDeniedException.getMessage());
    }

    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (request, response, authenticationException) ->
                response.sendError(HttpStatus.UNAUTHORIZED.value(), authenticationException.getMessage());
    }

}
