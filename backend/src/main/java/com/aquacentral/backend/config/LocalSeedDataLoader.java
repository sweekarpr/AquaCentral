package com.aquacentral.backend.config;

import com.aquacentral.backend.domain.User;
import com.aquacentral.backend.domain.VolumeUnit;
import com.aquacentral.backend.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Seeds one user when running with "local" profile and the database is empty.
 * Allows Tank API to work without setting up Postgres or manual user creation.
 */
@Component
@Profile("local")
public class LocalSeedDataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LocalSeedDataLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) return;
        User user = new User();
        user.setEmail("local@aquacentral.dev");
        user.setPasswordHash(passwordEncoder.encode("password"));
        user.setRole("USER");
        user.setEnabled(true);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        user.setVolumeUnit(VolumeUnit.GALLONS);
        userRepository.save(user);
    }
}
