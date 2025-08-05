// service/BookingService.java
package com.bookingservice.booking.service;

// Import the necessary Log4j2 classes
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bookingservice.booking.dto.*;
import com.bookingservice.booking.entity.Booking;
import com.bookingservice.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    // Get a logger instance for this class
    private static final Logger logger = LogManager.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GeospatialService geospatialService;

    @Value("${labcorp.service.url}")
    private String labcorpServiceUrl;

    private static final double MAX_DISTANCE_MILES = 5.0;

    @Transactional
    public BookingConfirmation createBooking(BookingRequest request) {
        logger.info("Received booking request for patientId: {} on date: {} for slot: {}",
                request.getPatientId(), request.getAvailabilityDate(), request.getSlotType());

        if (request.getLatitude() == null || request.getLongitude() == null) {
            logger.error("Booking request failed: User location (latitude and longitude) is required.");
            throw new RuntimeException("User location (latitude and longitude) is required.");
        }

        try {
            logger.debug("Fetching lab information from URL: {}", labcorpServiceUrl);
            LabcorpDTO[] allLabs = restTemplate.getForObject(labcorpServiceUrl, LabcorpDTO[].class);
            if (allLabs == null) {
                logger.error("Failed to retrieve lab information from Labcorp service. Response was null.");
                throw new RuntimeException("Could not retrieve lab information from Labcorp service.");
            }
            logger.info("Found {} labs to process.", allLabs.length);

            Optional<LabcorpDTO> suitableLab = Arrays.stream(allLabs)
                    .filter(lab -> lab.getLatitude() != null && lab.getLongitude() != null)
                    .filter(lab -> {
                        double distance = geospatialService.calculateDistance(
                                request.getLatitude(), request.getLongitude(),
                                lab.getLatitude(), lab.getLongitude()
                        );
                        logger.trace("Calculated distance for labId {}: {} miles", lab.getLabcorpId(), distance);
                        return distance <= MAX_DISTANCE_MILES;
                    })
                    .filter(lab -> lab.getAvailabilities() != null && lab.getAvailabilities().stream()
                            .anyMatch(avail -> avail.getAvailabilityDate().equals(request.getAvailabilityDate()) &&
                                    avail.getSlotType().equalsIgnoreCase(request.getSlotType()) &&
                                    avail.getAvailableCount() > 0))
                    .findFirst();

            if (suitableLab.isPresent()) {
                LabcorpDTO labToBook = suitableLab.get();
                logger.info("Found suitable lab: {} (ID: {})", labToBook.getName(), labToBook.getLabcorpId());

                Booking newBooking = new Booking();
                newBooking.setPatientId(request.getPatientId());
                newBooking.setLabcorpId(labToBook.getLabcorpId());
                newBooking.setBookingDate(request.getAvailabilityDate());
                newBooking.setSlotType(request.getSlotType().toUpperCase());

                Booking savedBooking = bookingRepository.save(newBooking);
                logger.info("Successfully created booking with ID: {} for patientId: {}", savedBooking.getBookingId(), savedBooking.getPatientId());

                return createBookingConfirmation(savedBooking, labToBook);
            } else {
                logger.warn("No available labs found within 5 miles for the requested time for patientId: {}", request.getPatientId());
                throw new RuntimeException("No available labs found within 5 miles for the requested time.");
            }
        } catch (Exception e) {
            logger.error("An unexpected error occurred during the booking process for patientId: {}", request.getPatientId(), e);
            throw e; // Re-throw the original exception after logging
        }
    }

    private BookingConfirmation createBookingConfirmation(Booking booking, LabcorpDTO lab) {
        // ... (rest of the method is the same)
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(booking.getBookingId());
        confirmation.setPatientId(booking.getPatientId());
        confirmation.setPatientName("Patient " + booking.getPatientId()); // Placeholder
        confirmation.setLabcorpId(lab.getLabcorpId());
        confirmation.setLabcorpName(lab.getName());
        confirmation.setBookingDate(booking.getBookingDate());
        confirmation.setSlotType(booking.getSlotType());
        return confirmation;
    }

    public Optional<BookingConfirmation> findBookingById(UUID bookingId) {
        logger.info("Searching for booking with ID: {}", bookingId);
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    LabcorpDTO lab = restTemplate.getForObject(labcorpServiceUrl + "/" + booking.getLabcorpId(), LabcorpDTO.class);
                    return createBookingConfirmation(booking, lab);
                });
    }

    public List<BookingConfirmation> findBookingsByPatientId(Integer patientId) {
        logger.info("Searching for all bookings for patient ID: {}", patientId);
        return bookingRepository.findByPatientId(patientId).stream()
                .map(booking -> {
                    LabcorpDTO lab = restTemplate.getForObject(labcorpServiceUrl + "/" + booking.getLabcorpId(), LabcorpDTO.class);
                    return createBookingConfirmation(booking, lab);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelBooking(UUID bookingId) {
        logger.info("Attempting to cancel booking with ID: {}", bookingId);
        if (!bookingRepository.existsById(bookingId)) {
            logger.warn("Attempted to cancel a booking that does not exist. ID: {}", bookingId);
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
        logger.info("Successfully cancelled booking with ID: {}", bookingId);
    }
}
