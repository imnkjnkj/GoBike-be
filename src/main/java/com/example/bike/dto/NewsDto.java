package com.example.bike.dto;

import com.example.bike.enumeration.NewsStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.example.bike.entity.News} entity
 */

@Schema
public record NewsDto(@JsonProperty(access = JsonProperty.Access.READ_ONLY) Integer id, @NotNull String title,
                      @NotNull String description, String thumbnail, String coverImage,
                      String sapo, @Schema @NotNull NewsStatus status, @NotNull NewsCategoryDto category,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY) NewsUserDto user,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY) Instant createdAt,
                      @JsonProperty(access = JsonProperty.Access.READ_ONLY) Instant updatedAt) implements Serializable {
}