package com.patientservice.patient.Service;

package com.bookingservice.booking.service;

//import com.bookingservice.booking.dto.*;
//import com.bookingservice.booking.model.Booking;
//import com.bookingservice.booking.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the BookingService using a TDD approach.
 * We use Mockito to simulate the behavior of external dependencies
 * like the RestTemplate and repositories.
 */
@ExtendWith(MockitoExtension.class)
class bookingservice {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private GeospatialService geospatialService;

    @InjectMocks // Injects the mocked dependencies into our service
    private BookingService bookingService;

    private BookingRequest bookingRequest;
    private PatientAddressDTO patientAddress;
    private LabcorpDTO availableLab;

    @BeforeEach
    void setUp() {
        // --- Test Data Setup ---
        bookingRequest = new BookingRequest();
        bookingRequest.setPatientId(1);
        bookingRequest.setAvailabilityDate(LocalDate.of(2025, 8, 20));
        bookingRequest.setSlotType("MORNING");

        patientAddress = new PatientAddressDTO();
        patientAddress.setLatitude(40.7128);
        patientAddress.setLongitude(-74.0060);

        LabcorpAvailabilityDTO availability = new LabcorpAvailabilityDTO();
        availability.setAvailableCount(5);
        availability.setSlotType("MORNING");
        availability.setAvailabilityDate(LocalDate.of(2025, 8, 20));

        availableLab = new LabcorpDTO();
        availableLab.setLabcorpId(101);
        availableLab.setName("Downtown Health Lab");
        availableLab.setLatitude(40.7129); // Very close to the patient
        availableLab.setLongitude(-74.0061);
        availableLab.setAvailabilities(Collections.singletonList(availability));
    }

    /**
     * RED: Test that a booking is created successfully when a lab is available nearby.
     * GREEN: Implement the createBooking logic to make this pass.
     * REFACTOR: Clean up the implementation.
     */
    @Test
    void shouldCreateBooking_WhenLabIsAvailableNearby() {
        // --- Arrange (Given) ---
        // Mock the external service calls
        when(restTemplate.getForObject("http://localhost:8081/api/patients/1/address", PatientAddressDTO.class))
                .thenReturn(patientAddress);
        when(restTemplate.getForObject("http://localhost:8082/api/labs", LabcorpDTO[].class))
                .thenReturn(new LabcorpDTO[]{availableLab});
        when(geospatialService.calculateDistance(any(Double.class), any(Double.class), any(Double.class), any(Double.class)))
                .thenReturn(0.1); // Simulate a distance of 0.1 miles
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> {
            Booking b = invocation.getArgument(0);
            b.setBookingId(UUID.randomUUID()); // Simulate saving and getting an ID
            return b;
        });

        // --- Act (When) ---
        BookingConfirmation confirmation = bookingService.createBooking(bookingRequest);

        // --- Assert (Then) ---
        assertNotNull(confirmation);
        assertEquals(1, confirmation.getPatientId());
        assertEquals(101, confirmation.getLabcorpId());
        assertEquals("Downtown Health Lab", confirmation.getLabcorpName());
        assertEquals("MORNING", confirmation.getSlotType());
    }

    /**
     * RED: Test that an exception is thrown if no labs are found within the radius.
     * GREEN: Add the check in the service to throw the exception.
     */
    @Test
    void shouldThrowException_WhenNoLabsAreNearby() {
        // --- Arrange (Given) ---
        when(restTemplate.getForObject(anyString(), any(Class.class)))
                .thenReturn(patientAddress) // First call for address
                .thenReturn(new LabcorpDTO[]{availableLab}); // Second call for labs
        when(geospatialService.calculateDistance(any(Double.class), any(Double.class), any(Double.class), any(Double.class)))
                .thenReturn(10.0); // Simulate a distance of 10 miles (outside the 5-mile radius)

        // --- Act & Assert (When & Then) ---
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(bookingRequest);
        });

        assertEquals("No available labs found for the requested time and location.", exception.getMessage());
    }

    /**
     * RED: Test that an exception is thrown if the patient's address cannot be found.
     * GREEN: Add the null check for the address.
     */
    @Test
    void shouldThrowException_WhenPatientAddressIsMissing() {
        // --- Arrange (Given) ---
        when(restTemplate.getForObject("http://localhost:8081/api/patients/1/address", PatientAddressDTO.class))
                .thenReturn(null); // Simulate patient address not found

        // --- Act & Assert (When & Then) ---
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.createBooking(bookingRequest);
        });

        assertEquals("Patient address not found or is incomplete.", exception.getMessage());
    }
}

