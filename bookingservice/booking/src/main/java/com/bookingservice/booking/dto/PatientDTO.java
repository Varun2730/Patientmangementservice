package com.bookingservice.booking.dto;

// dto/PatientDTO.java

/**
 * DTO to represent patient data received from the Patient microservice.
 */
public class PatientDTO {

    private Integer patientId;
    private String firstName;
    private String lastName;
    private String email;
    private PatientAddressDTO address;

    // --- Getters and Setters ---

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PatientAddressDTO getAddress() {
        return address;
    }

    public void setAddress(PatientAddressDTO address) {
        this.address = address;
    }
}
