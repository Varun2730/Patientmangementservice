package com.labcorpservice.labcorp.repository;

import com.labcorpservice.labcorp.entity.LabcorpAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LabcorpAvailability entity.
 */
@Repository
public interface LabcorpAvailabilityRepository extends JpaRepository<LabcorpAvailability, Integer> {
    // You can define custom query methods here. For example, to find availability
    // for a specific lab on a specific date.
    // List<LabcorpAvailability> findByLabcorp_LabcorpIdAndAvailabilityDate(Integer labcorpId, LocalDate date);
}
