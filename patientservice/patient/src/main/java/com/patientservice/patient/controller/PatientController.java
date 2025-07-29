package com.patientservice.patient.controller;



import com.patientservice.patient.entity.PatientDetails;
import com.patientservice.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing patients.
 * This provides the API endpoints that other services will call.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * GET /api/patients
     * Fetches a list of all patients.
     * @return A list of all patients.
     */
    @GetMapping
    public List<PatientDetails> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * GET /api/patients/{id}
     * Fetches a single patient by their ID. This is the primary endpoint
     * the Booking microservice will use.
     *
     * @param id The patient's ID.
     * @return A ResponseEntity containing the patient if found, or 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDetails> getPatientById(@PathVariable Integer id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok) // If patient exists, return 200 OK with patient body
                .orElse(ResponseEntity.notFound().build()); // Otherwise, return 404 Not Found
    }

    /**
     * POST /api/patients
     * Creates a new patient.
     * @param patientDetails The patient data from the request body.
     * @return The created patient with their new ID.
     */
    @PostMapping
    public PatientDetails createPatient(@RequestBody PatientDetails patientDetails) {
        return patientService.createPatient(patientDetails);
    }
}
