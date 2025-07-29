package com.bookingservice.booking.dto;


import java.util.List;

/**
 * DTO to represent lab data received from the Labcorp microservice.
 */
public class LabcorpDTO {

    private Integer labcorpId;
    private String name;
    private Double latitude;
    private Double longitude;
    private LabcorpAvailabilityDTO availability;
    private List<LabcorpAvailabilityDTO> availabilities;


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

    public LabcorpAvailabilityDTO getAvailability() {
        return availability;
    }

    public void setAvailability(LabcorpAvailabilityDTO availability) {
        this.availability = availability;
    }

    // Corrected getter and setter for the list
    public List<LabcorpAvailabilityDTO> getAvailabilities() { return availabilities; }
    public void setAvailabilities(List<LabcorpAvailabilityDTO> availabilities) { this.availabilities = availabilities; }
}


