// repository/BookingRepository.java
// Make sure your package declaration matches your file structure
package com.bookingservice.booking.repository;

import com.bookingservice.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Booking entity.
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    /**
     * Finds all bookings associated with a specific patient ID.
     * Spring Data JPA will automatically create the query based on the method name.
     *
     * @param patientId The ID of the patient.
     * @return A list of bookings for that patient.
     */
    List<Booking> findByPatientId(Integer patientId);
}
