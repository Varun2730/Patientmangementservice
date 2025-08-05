// repository/BookingRepository.java
package com.bookingservice.booking.repository;

import com.bookingservice.booking.entity.Booking; // Corrected package
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate; // Import LocalDate
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    /**
     * Finds all bookings associated with a specific patient ID.
     */
    List<Booking> findByPatientId(Integer patientId);

    /**
     * --- NEW METHOD FOR BATCH PROCESSOR ---
     * Checks if a booking already exists for a specific patient, lab, date, and slot.
     * This prevents the batch job from creating duplicate bookings.
     * Spring Data JPA automatically generates the required SQL query from this method name.
     */
    boolean existsByPatientIdAndLabcorpIdAndBookingDateAndSlotType(
            Integer patientId, Integer labcorpId, LocalDate bookingDate, String slotType);
}
