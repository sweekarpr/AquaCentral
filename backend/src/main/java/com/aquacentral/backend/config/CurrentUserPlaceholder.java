package com.aquacentral.backend.config;

import org.springframework.stereotype.Component;
import com.aquacentral.backend.domain.User;
import com.aquacentral.backend.repository.UserRepository;

import java.util.Optional;

@Component
public class CurrentUserPlaceholder {
    private final UserRepository userRepository;

    public CurrentUserPlaceholder(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public User getCurrentUser() {
        Optional<User> user = userRepository.findAll().stream().findFirst();

        return user.orElseThrow(() -> new IllegalStateException("No user found"));
    }

}