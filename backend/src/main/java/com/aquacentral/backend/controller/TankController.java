package com.aquacentral.backend.controller;

import com.aquacentral.backend.config.CurrentUserPlaceholder;
import com.aquacentral.backend.domain.Tank;
import com.aquacentral.backend.domain.User;
import com.aquacentral.backend.dto.TankRequest;
import com.aquacentral.backend.dto.TankResponse;
import com.aquacentral.backend.repository.TankRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/tanks")
public class TankController {

    private final TankRepository tankRepository;
    private final CurrentUserPlaceholder currentUserPlaceholder;

    public TankController(TankRepository tankRepository, CurrentUserPlaceholder currentUserPlaceholder) {
        this.tankRepository = tankRepository;
        this.currentUserPlaceholder = currentUserPlaceholder;
    }

    @GetMapping
    public List<TankResponse> getAllTanks() {
        User currentUser = currentUserPlaceholder.getCurrentUser();
        List<Tank> tanks = tankRepository.findByOwner(currentUser);
        return tanks.stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public TankResponse getTank(@PathVariable Long id) {
        Tank tank = tankRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found"));
        User currentUser = currentUserPlaceholder.getCurrentUser();
        if (!tank.getOwner().getId().equals(currentUser.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tank not found");
        }
        return toResponse(tank);
    }

    @PostMapping
    public TankResponse createTank(@Valid @RequestBody TankRequest request) {
        User currentUser = currentUserPlaceholder.getCurrentUser();
        Tank tank = new Tank();
        tank.setName(request.name());
        tank.setType(request.type());
        tank.setVolume(request.volume());
        tank.setVolumeUnit(request.volumeUnit());
        tank.setDescription(request.description() != null ? request.description() : "");
        tank.setCreatedAt(Instant.now());
        tank.setOwner(currentUser);
        tank = tankRepository.save(tank);
        return toResponse(tank);
    }

    private TankResponse toResponse(Tank tank) {
        return new TankResponse(
            tank.getId(),
            tank.getName(),
            tank.getType(),
            tank.getVolume(),
            tank.getVolumeUnit(),
            tank.getDescription(),
            tank.getCreatedAt(),
            tank.getOwner().getId()
        );
    }
}
