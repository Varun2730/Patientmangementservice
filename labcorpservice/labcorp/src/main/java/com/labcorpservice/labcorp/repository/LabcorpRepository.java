package com.labcorpservice.labcorp.repository;

import com.labcorpservice.labcorp.entity.Labcorp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Labcorp entity.
 * This interface provides all the basic CRUD (Create, Read, Update, Delete) operations.
 */
@Repository
public interface LabcorpRepository extends JpaRepository<Labcorp, Integer> {
    // JpaRepository provides methods like findAll(), findById(), save(), delete() out of the box.
    // You can add custom query methods here if needed later.
}
