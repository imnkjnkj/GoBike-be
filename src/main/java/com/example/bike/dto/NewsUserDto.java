package com.example.bike.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.bike.entity.User} entity
 */
public record NewsUserDto(@NotNull String email) implements Serializable {
}