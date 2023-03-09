package com.example.bike.dto;

import jakarta.validation.constraints.NotBlank;

public record JwtTokenDTO(@NotBlank String accessToken) {
}
