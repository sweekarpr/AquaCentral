package com.aquacentral.backend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

/**
 * User entity.
 * 
 * This entity represents a user in the application.
 * It contains the user's email, password, role, enabled status, created at, and tanks.
 * 
 * Note:
 * This entity is used to store user information in the database.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private boolean enabled = true;

    private Instant createdAt;

    private Instant updatedAt;

    @Column(nullable = false)
    private VolumeUnit volumeUnit;

    @OneToMany(mappedBy = "owner")
    private List<Tank> tanks;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    public VolumeUnit getVolumeUnit() { return volumeUnit; }
    public void setVolumeUnit(VolumeUnit volumeUnit) { this.volumeUnit = volumeUnit; }
    public List<Tank> getTanks() { return tanks; }
    public void setTanks(List<Tank> tanks) { this.tanks = tanks; }
}