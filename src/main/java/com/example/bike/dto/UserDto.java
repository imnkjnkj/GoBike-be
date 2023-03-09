package com.example.bike.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.example.bike.entity.User} entity
 */
public record UserDto(Integer id, @NotNull String username, @NotNull String email,
                      Set<RoleDto> roles) implements Serializable {
}