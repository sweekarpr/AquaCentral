package com.aquacentral.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aquacentral.backend.domain.Tank;
import com.aquacentral.backend.domain.User;

@Repository
public interface TankRepository extends JpaRepository<Tank, Long> {

    List<Tank> findByOwner(User owner);

    boolean existsByName(String name);

}