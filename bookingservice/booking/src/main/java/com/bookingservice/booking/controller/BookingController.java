// Corrected package to match your directory structure
package com.bookingservice.booking.controller;


import com.bookingservice.booking.dto.BookingConfirmation;
import com.bookingservice.booking.dto.BookingRequest;
import com.bookingservice.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for handling booking requests.
 * This is the main entry point for the Booking microservice.
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * Creates a new booking.
     *
     * @param request The booking request details.
     * @return A ResponseEntity containing the booking confirmation or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try {
            BookingConfirmation confirmation = bookingService.createBooking(request);
            return ResponseEntity.ok(confirmation);
        } catch (RuntimeException e) {
            // In a real application, you'd have more sophisticated error handling
            // and return appropriate HTTP status codes (e.g., 404, 409, 503).
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Retrieves a single booking by its UUID.
     *
     * @param bookingId The UUID of the booking.
     * @return A ResponseEntity containing the booking confirmation or a 404 Not Found error.
     */
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingConfirmation> getBookingById(@PathVariable UUID bookingId) {
        // This will require a new method in BookingService
        return bookingService.findBookingById(bookingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all bookings for a specific patient.
     *
     * @param patientId The ID of the patient.
     * @return A ResponseEntity containing a list of booking confirmations.
     */
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<BookingConfirmation>> getBookingsByPatient(@PathVariable Integer patientId) {
        // This will require a new method in BookingService
        List<BookingConfirmation> bookings = bookingService.findBookingsByPatientId(patientId);
        return ResponseEntity.ok(bookings);
    }

    /**
     * Cancels a booking by its UUID.
     *
     * @param bookingId The UUID of the booking to cancel.
     * @return A ResponseEntity with no content (204) on success.
     */
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable UUID bookingId) {
        try {
            // This will require a new method in BookingService
            bookingService.cancelBooking(bookingId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // This could be a custom exception like BookingNotFoundException
            return ResponseEntity.notFound().build();
        }
    }
}
