package com.example.bike.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.bike.entity.Category} entity
 */
public record NewsCategoryDto(@NotNull Integer id) implements Serializable {
}