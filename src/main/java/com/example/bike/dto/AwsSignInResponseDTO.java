package com.example.bike.dto;

public record AwsSignInResponseDTO(String accessToken, String idToken, String refreshToken, String tokenType,Long expiresIn) {}
