package com.aquacentral.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers
				.frameOptions(frameOptions -> frameOptions.deny())
				.contentSecurityPolicy(csp -> csp
					.policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval'; style-src 'self' 'unsafe-inline';")
				)
				.httpStrictTransportSecurity(hsts -> hsts
					.maxAgeInSeconds(31536000)
					.includeSubdomains(true)
				)
			)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
				.requestMatchers("/actuator/health").permitAll()
				.anyRequest().permitAll()
			);

		return http.build();
	}
}

