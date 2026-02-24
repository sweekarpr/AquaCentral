package com.aquacentral.backend.dto;

import com.aquacentral.backend.domain.TankType;
import com.aquacentral.backend.domain.VolumeUnit;

import java.time.Instant;

/**
 * API response for a single tank.
 */
public record TankResponse(
    Long id,
    String name,
    TankType type,
    Double volume,
    VolumeUnit volumeUnit,
    String description,
    Instant createdAt,
    Long ownerId
) {}
