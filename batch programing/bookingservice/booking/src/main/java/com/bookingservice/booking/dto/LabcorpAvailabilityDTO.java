package com.bookingservice.booking.dto;

import java.time.LocalDate;

/**
 * DTO to represent lab availability data.
 */
public class LabcorpAvailabilityDTO {

    // --- NEW FIELD ---
    // This is needed to track which lab this availability belongs to.
    private Integer labcorpId;

    private LocalDate availabilityDate;
    private String slotType;
    private int availableCount;

    // --- Getters and Setters (including the new ones) ---

    public Integer getLabcorpId() {
        return labcorpId;
    }

    public void setLabcorpId(Integer labcorpId) {
        this.labcorpId = labcorpId;
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

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
}