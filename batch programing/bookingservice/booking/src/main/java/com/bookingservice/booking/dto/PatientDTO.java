package com.bookingservice.booking.dto;

// Add this import for the annotation
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO to represent patient data received from the Patient microservice.
 * Updated with @JsonProperty to handle potential mismatches in field names.
 */
public class PatientDTO {

    // Maps the JSON field "patientid" to the Java field "patientId"
    @JsonProperty("patientid")
    private Integer patientId;

    // Maps the JSON field "fname" to the Java field "firstName"
    @JsonProperty("fname")
    private String firstName;

    // Maps the JSON field "lname" to the Java field "lastName"
    @JsonProperty("lname")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
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
