package com.aquacentral.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import java.time.Instant;

/**
 * Tank entity (aggregate root).
 * Owned by a User; has name, volume, type, description, and creation date.
 */

@Entity
@Table(name = "tanks")
public class Tank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private TankType type;

    @Column(nullable = false)
    private Double volume;

    @Column(nullable = false)
    private VolumeUnit volumeUnit;

    private Instant createdAt;

    @Column(length = 2000)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public TankType getType() { return type; }
    public void setType(TankType type) { this.type = type; }
    public Double getVolume() { return volume; }
    public void setVolume(Double volume) { this.volume = volume; }
    public VolumeUnit getVolumeUnit() { return volumeUnit; }
    public void setVolumeUnit(VolumeUnit volumeUnit) { this.volumeUnit = volumeUnit; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
}