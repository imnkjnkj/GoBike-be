package com.example.bike.dto;

import com.example.bike.enumeration.RoleName;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.bike.entity.Role} entity
 */
public record RoleDto(RoleName name) implements Serializable {
}