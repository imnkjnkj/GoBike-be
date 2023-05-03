package com.example.bike.dto;

import com.example.bike.entity.Bicycle;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * A DTO for the {@link Bicycle} entity
 */
public record BicycleDto(Instant createdAt, Instant updatedAt, Integer id, String name, String thumbnail,
                         List<String> images, JsonNode information, JsonNode suitableUser,
                         JsonNode transmissionSystem, JsonNode frame, JsonNode wheelset) implements Serializable {
}