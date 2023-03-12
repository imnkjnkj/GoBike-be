package com.example.bike.dto;

import com.example.bike.enumeration.NewsStatus;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.example.bike.entity.News} entity
 */
public record NewsDto(Integer id, @NotNull String title, @NotNull String description, String thumbnail,
                      String coverImage, NewsStatus status, @NotNull CategoryNewsDto category,
                      Instant updateAt) implements Serializable {
}