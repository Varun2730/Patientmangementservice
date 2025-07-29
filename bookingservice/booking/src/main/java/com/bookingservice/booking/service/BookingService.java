// service/BookingService.java
package com.bookingservice.booking.service;

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
        if (request.getLatitude() == null || request.getLongitude() == null) {
            throw new RuntimeException("User location (latitude and longitude) is required.");
        }

        LabcorpDTO[] allLabs = restTemplate.getForObject(labcorpServiceUrl, LabcorpDTO[].class);
        if (allLabs == null) {
            throw new RuntimeException("Could not retrieve lab information from Labcorp service.");
        }

        Optional<LabcorpDTO> suitableLab = Arrays.stream(allLabs)
                .filter(lab -> lab.getLatitude() != null && lab.getLongitude() != null)
                .filter(lab -> {
                    double distance = geospatialService.calculateDistance(
                            request.getLatitude(), request.getLongitude(),
                            lab.getLatitude(), lab.getLongitude()
                    );
                    return distance <= MAX_DISTANCE_MILES;
                })
                .filter(lab -> lab.getAvailabilities() != null && lab.getAvailabilities().stream()
                        .anyMatch(avail -> avail.getAvailabilityDate().equals(request.getAvailabilityDate()) &&
                                avail.getSlotType().equalsIgnoreCase(request.getSlotType()) &&
                                avail.getAvailableCount() > 0))
                .findFirst();

        if (suitableLab.isPresent()) {
            LabcorpDTO labToBook = suitableLab.get();

            Booking newBooking = new Booking();
            newBooking.setPatientId(request.getPatientId());
            newBooking.setLabcorpId(labToBook.getLabcorpId());
            newBooking.setBookingDate(request.getAvailabilityDate());
            newBooking.setSlotType(request.getSlotType().toUpperCase());

            Booking savedBooking = bookingRepository.save(newBooking);

            return createBookingConfirmation(savedBooking, labToBook);
        } else {
            throw new RuntimeException("No available labs found within 5 miles for the requested time.");
        }
    }

    // Other service methods...

    private BookingConfirmation createBookingConfirmation(Booking booking, LabcorpDTO lab) {
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
        return bookingRepository.findById(bookingId)
                .map(booking -> {
                    LabcorpDTO lab = restTemplate.getForObject(labcorpServiceUrl + "/" + booking.getLabcorpId(), LabcorpDTO.class);
                    return createBookingConfirmation(booking, lab);
                });
    }

    public List<BookingConfirmation> findBookingsByPatientId(Integer patientId) {
        return bookingRepository.findByPatientId(patientId).stream()
                .map(booking -> {
                    LabcorpDTO lab = restTemplate.getForObject(labcorpServiceUrl + "/" + booking.getLabcorpId(), LabcorpDTO.class);
                    return createBookingConfirmation(booking, lab);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void cancelBooking(UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }
}
