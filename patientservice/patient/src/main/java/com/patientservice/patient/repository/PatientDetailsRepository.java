package com.patientservice.patient.repository;



import com.patientservice.patient.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PatientDetails entity.
 */
@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Integer> {
    // JpaRepository provides all standard CRUD operations (save, findById, findAll, delete, etc.)
}
