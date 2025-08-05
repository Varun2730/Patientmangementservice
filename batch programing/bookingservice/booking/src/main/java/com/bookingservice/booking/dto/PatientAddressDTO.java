package com.bookingservice.booking.dto;

/**
 * DTO for the patient's address, containing geographic coordinates.
 * This will be nested within the PatientDTO.
 */
public class PatientAddressDTO {

    private Double latitude;
    private Double longitude;

    // --- Getters and Setters ---

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
