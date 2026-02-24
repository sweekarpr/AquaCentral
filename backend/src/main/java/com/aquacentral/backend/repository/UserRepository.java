package com.aquacentral.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aquacentral.backend.domain.User;

/**
 * User repository.
 * 
 * This repository is used to store and retrieve user information from the database.
 * 
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


}
