package com.patientservice.patient.repository;



import com.patientservice.patient.entity.PatientAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the PatientAddress entity.
 */
@Repository
public interface PatientAddressRepository extends JpaRepository<PatientAddress, Integer> {

    /**
     * Finds a patient's address by their patient ID.
     * This will be a crucial method for the Booking service.
     *
     * @param patientId The ID of the patient.
     * @return An Optional containing the patient's address if it exists.
     */
    Optional<PatientAddress> findByPatientId(Integer patientId);
}
