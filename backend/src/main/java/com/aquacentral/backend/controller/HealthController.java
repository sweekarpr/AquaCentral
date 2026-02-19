package com.aquacentral.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aquacentral.backend.dto.HealthResponse;

import java.time.Instant;

/**
 * Controller for the health endpoint.
 * 
 * This controller provides a health check endpoint for the application.
 * It returns a health response with the application name, timestamp, and active profile.
 * 
 * Note:
 * This controller is used for monitoring the health of the application.
 * It is not used for authentication or authorization.
 */

@RestController
@RequestMapping("/api")
public class HealthController {

    private final Environment environment;

    @Value("${spring.application.name}")
    private String appName;

    public HealthController(Environment environment) {
        this.environment = environment;
    }

/** 
 * Health check endpoint.
 * 
 * This endpoint returns a health response with the application name, timestamp, and active profile.
 * 
 * Note:
 * This endpoint is used for monitoring the health of the application.
 * It is not used for authentication or authorization.
 */

    @GetMapping("/health")
    public HealthResponse health() {

        String[] activeProfiles = environment.getActiveProfiles();
        String profile = activeProfiles.length > 0 ? activeProfiles[0] : "default";
    
        return new HealthResponse(
            appName,
            Instant.now(),
            profile
        );
    }
}