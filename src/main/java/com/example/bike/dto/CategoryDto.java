package com.example.bike.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.bike.entity.Category} entity
 */
public record CategoryDto(@JsonProperty(access = JsonProperty.Access.READ_ONLY) Integer id,
                          @NotBlank String name, @NotBlank String description) implements Serializable {
}