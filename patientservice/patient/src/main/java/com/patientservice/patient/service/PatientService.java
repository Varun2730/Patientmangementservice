package com.patientservice.patient.service;



import com.patientservice.patient.entity.PatientDetails;
import com.patientservice.patient.repository.PatientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer containing business logic for patient operations.
 */
@Service
public class PatientService {

    private final PatientDetailsRepository patientDetailsRepository;

    @Autowired
    public PatientService(PatientDetailsRepository patientDetailsRepository) {
        this.patientDetailsRepository = patientDetailsRepository;
    }

    /**
     * Retrieves all patients from the database.
     * @return A list of all PatientDetails objects.
     */
    public List<PatientDetails> getAllPatients() {
        return patientDetailsRepository.findAll();
    }

    /**
     * Finds a single patient by their unique ID.
     * The address and availability will be fetched along with the patient details
     * due to the relationships defined in the entity.
     *
     * @param patientId The ID of the patient to find.
     * @return An Optional containing the PatientDetails if found, otherwise an empty Optional.
     */
    public Optional<PatientDetails> getPatientById(Integer patientId) {
        return patientDetailsRepository.findById(patientId);
    }

    /**
     * Creates and saves a new patient.
     * @param patientDetails The patient object to save.
     * @return The saved PatientDetails object, including the generated ID.
     */
    public PatientDetails createPatient(PatientDetails patientDetails) {
        // In a real application, you'd add validation and error handling here
        return patientDetailsRepository.save(patientDetails);
    }
}
