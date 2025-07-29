package com.patientservice.patient.repository;



import com.patientservice.patient.entity.PatientAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PatientAvailability entity.
 */
@Repository
public interface PatientAvailabilityRepository extends JpaRepository<PatientAvailability, Integer> {
    // You could add custom queries here later if needed, e.g., to find all availabilities for a patient.
}
