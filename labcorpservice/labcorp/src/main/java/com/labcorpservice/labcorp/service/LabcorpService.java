package com.labcorpservice.labcorp.service;

import com.labcorpservice.labcorp.entity.Labcorp;
import com.labcorpservice.labcorp.repository.LabcorpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class containing business logic for Labcorp operations.
 */
@Service
public class LabcorpService {

    private final LabcorpRepository labcorpRepository;

    @Autowired
    public LabcorpService(LabcorpRepository labcorpRepository) {
        this.labcorpRepository = labcorpRepository;
    }

    /**
     * Finds all labs in the database.
     * @return A list of all Labcorp entities.
     */
    public List<Labcorp> findAllLabs() {
        return labcorpRepository.findAll();
    }

    /**
     * Finds a single lab by its primary key.
     * @param id The ID of the lab.
     * @return An Optional containing the lab if found, otherwise an empty Optional.
     */
    public Optional<Labcorp> findLabById(Integer id) {
        return labcorpRepository.findById(id);
    }

    /**
     * Saves a new lab or updates an existing one.
     * @param labcorp The lab entity to save.
     * @return The saved lab entity.
     */
    public Labcorp saveLab(Labcorp labcorp) {
        return labcorpRepository.save(labcorp);
    }
}
