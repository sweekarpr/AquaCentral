package com.aquacentral.backend.dto;

import java.time.Instant;

/**
 * DTO for the health response.
 * 
 * This DTO is used to return the health response from the health controller.
 * It contains the application name, timestamp, and active profile.
 * 
 * Note:
 * This DTO is used for monitoring the health of the application.
 */

public record HealthResponse(
    String appName,
    Instant timestamp,
    String activeProfile
) {}