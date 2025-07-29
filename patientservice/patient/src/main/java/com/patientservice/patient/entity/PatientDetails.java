package com.patientservice.patient.entity;



//import jakarta.persistence.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents the patient_details table.
 * This is the primary entity for the Patient microservice.
 */
@Entity
@Table(name = "patient_details")
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gender; // MALE, FEMALE, OTHER

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Column(name = "created_at", updatable = false, insertable = false)
    private OffsetDateTime createdAt;

    // --- Relationships ---

    // One patient has one address
    @OneToOne(mappedBy = "patientDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PatientAddress address;

    // One patient can have many availability slots
    @OneToMany(mappedBy = "patientDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PatientAvailability> availabilities;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PatientAddress getAddress() {
        return address;
    }

    public void setAddress(PatientAddress address) {
        this.address = address;
    }

    public List<PatientAvailability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<PatientAvailability> availabilities) {
        this.availabilities = availabilities;
    }
}
