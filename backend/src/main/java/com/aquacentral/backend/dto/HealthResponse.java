package com.aquacentral.backend.dto;

import java.time.Instant;

public record HealthResponse(
    String appName,
    Instant timestamp,
    String activeProfile
) {}