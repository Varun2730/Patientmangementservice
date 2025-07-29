package com.labcorpservice.labcorp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents the 'labcorp' table in the database.
 */

@Entity
@Table(name = "labcorp")
public class Labcorp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "labcorp_id")
    private Integer labcorpId;

    private String name;
    private String email;
    private String phone;

    // These fields are crucial for the booking service's distance calculation
    private Double latitude;
    private Double longitude;

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMPTZ DEFAULT NOW()")
    private OffsetDateTime createdAt;

    // Establishes the one-to-many relationship with LabcorpAvailability
    @OneToMany(mappedBy = "labcorp", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Manages serialization to prevent infinite loops
    private List<LabcorpAvailability> availabilities;

    // --- Constructors ---
    public Labcorp() {
    }

    // --- Getters and Setters ---
    public Integer getLabcorpId() {
        return labcorpId;
    }

    public void setLabcorpId(Integer labcorpId) {
        this.labcorpId = labcorpId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<LabcorpAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<LabcorpAvailability> availabilities) {
        this.availabilities = availabilities;
    }

    // --- equals() and hashCode() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Labcorp labcorp = (Labcorp) o;
        return Objects.equals(labcorpId, labcorp.labcorpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labcorpId);
    }
}
