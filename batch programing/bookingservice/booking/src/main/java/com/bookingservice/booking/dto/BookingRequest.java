// In your dto/BookingRequest.java file
package com.bookingservice.booking.dto;

import java.time.LocalDate;

public class BookingRequest {

    private Integer patientId;
    private LocalDate availabilityDate;
    private String slotType;

    // The fields are here, but they need methods
    private Double latitude;
    private Double longitude;

    // --- Getters and Setters (This is the missing part) ---

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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
}