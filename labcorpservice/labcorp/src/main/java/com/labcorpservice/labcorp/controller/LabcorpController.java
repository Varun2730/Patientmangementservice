package com.labcorpservice.labcorp.controller;

import com.labcorpservice.labcorp.entity.Labcorp;
import com.labcorpservice.labcorp.service.LabcorpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller to expose endpoints for Labcorp data.
 */
@RestController
@RequestMapping("/api/labs")
public class LabcorpController {

    private final LabcorpService labcorpService;

    @Autowired
    public LabcorpController(LabcorpService labcorpService) {
        this.labcorpService = labcorpService;
    }

    /**
     * Endpoint to get all available labs.
     * This will be consumed by the Booking microservice.
     * @return A list of all labs.
     */
    @GetMapping
    public List<Labcorp> getAllLabs() {
        return labcorpService.findAllLabs();
    }

    /**
     * Endpoint to get a single lab by its ID.
     * @param id The ID of the lab.
     * @return The Labcorp entity if found, otherwise a 404 Not Found response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Labcorp> getLabById(@PathVariable Integer id) {
        return labcorpService.findLabById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to create a new lab.
     * @param labcorp The lab details from the request body.
     * @return The created lab with a 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Labcorp> createLab(@RequestBody Labcorp labcorp) {
        Labcorp savedLab = labcorpService.saveLab(labcorp);
        return ResponseEntity.status(201).body(savedLab);
    }
}
