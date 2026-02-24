package com.aquacentral.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



/** 
 * Security configuration for the application.
 * 
 * This class defines HTTP security rules for the application.
 * 
 * Current behavior:
 * - Allows unauthenticated access:
 *   - /api/health (custom health endpoint)
 *   - Swagger / API documentation endpoints
 * - Requires authentication for all other endpoints.
 * - Enables default Spring Security form-based login
 * - Enables logout support
 * 
 * Note:
 * This configuration is mininal and intended for development.
 * It will likely be replaced or extended when implementing
 * JWT authentication or role-based access control.
 * 
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * Configures the security filter chain.
	 * 
	 * @param http The HttpSecurity object to configure
	 * @return The SecurityFilterChain
	 * @throws Exception If an error occurs while configuring the security filter chain
	 */

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth

				// Public endpoints (no authentication required)
				.requestMatchers("/api/health", "/api/tanks", "/api/tanks/**", "/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

				// All other endpoints require authentication
				.anyRequest().authenticated()
			)

			// Enables default Spring Security login form
			.formLogin(form -> form.permitAll())

			// Enables logout functionality
			.logout(logout -> logout.permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
