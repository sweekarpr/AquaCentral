package com.aquacentral.backend.dto;

import com.aquacentral.backend.domain.TankType;
import com.aquacentral.backend.domain.VolumeUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Request body for creating or updating a tank.
 * Owner is set by the server from the current user.
 */
public record TankRequest(
    @NotBlank String name,
    @NotNull TankType type,
    @NotNull @Positive Double volume,
    @NotNull VolumeUnit volumeUnit,
    String description
) {}
