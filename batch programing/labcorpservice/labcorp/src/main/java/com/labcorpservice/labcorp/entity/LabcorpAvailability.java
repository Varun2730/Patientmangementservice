package com.labcorpservice.labcorp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the 'labcorp_availability' table.
 */
@Entity
@Table(name = "labcorp_availability")
public class LabcorpAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Integer availabilityId;

    @Column(name = "availability_date", nullable = false)
    private LocalDate availabilityDate;

    @Column(name = "slot_type", nullable = false)
    private String slotType; // e.g., 'MORNING', 'AFTERNOON', 'EVENING'

    @Column(name = "available_count", nullable = false)
    private Integer availableCount;

    // Establishes the many-to-one relationship back to Labcorp
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "labcorp_id", nullable = false)
    @JsonBackReference // Manages serialization to prevent infinite loops
    private Labcorp labcorp;

    // --- Constructors ---
    public LabcorpAvailability() {
    }

    // --- Getters and Setters ---
    public Integer getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Integer availabilityId) {
        this.availabilityId = availabilityId;
    }

    public LocalDate getAvailabilityDate() {
        return availabilityDate;
    }

    public void setAvailabilityDate(LocalDate availabilityDate) {
        this.availabilityDate = availabilityDate;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public Labcorp getLabcorp() {
        return labcorp;
    }

    public void setLabcorp(Labcorp labcorp) {
        this.labcorp = labcorp;
    }

    // --- equals() and hashCode() ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabcorpAvailability that = (LabcorpAvailability) o;
        return Objects.equals(availabilityId, that.availabilityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availabilityId);
    }
}
