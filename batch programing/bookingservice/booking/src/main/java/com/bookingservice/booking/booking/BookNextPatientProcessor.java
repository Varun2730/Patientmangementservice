package com.bookingservice.booking.booking;

import com.bookingservice.booking.dto.LabcorpAvailabilityDTO;
import com.bookingservice.booking.dto.PatientDTO;
import com.bookingservice.booking.entity.Booking;
import com.bookingservice.booking.repository.BookingRepository; // Import the repository
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class BookNextPatientProcessor implements ItemProcessor<LabcorpAvailabilityDTO, Booking> {

    @Autowired
    private RestTemplate restTemplate;

    // --- NEW: Inject the BookingRepository to check for existing bookings ---
    @Autowired
    private BookingRepository bookingRepository;

    @Value("${patient.service.url}")
    private String patientServiceUrl;

    private List<PatientDTO> allPatients;
    private int nextPatientIndex = 0;

    private void fetchAllPatients() {
        System.out.println("PROCESSOR: Fetching all patients for round-robin booking...");
        PatientDTO[] patients = restTemplate.getForObject(patientServiceUrl, PatientDTO[].class);
        if (patients != null) {
            this.allPatients = Arrays.asList(patients);
        }
        System.out.println("PROCESSOR: Found " + (allPatients != null ? allPatients.size() : 0) + " patients.");
    }

    @Override
    public Booking process(LabcorpAvailabilityDTO openSlot) {
        if (allPatients == null) {
            fetchAllPatients();
        }

        if (allPatients == null || allPatients.isEmpty()) {
            System.out.println("PROCESSOR: No patients found to book. Skipping slot.");
            return null; // Skip this item
        }

        PatientDTO patientToBook = allPatients.get(nextPatientIndex);
        nextPatientIndex = (nextPatientIndex + 1) % allPatients.size();

        // --- NEW: Check if this booking already exists ---
        boolean bookingExists = bookingRepository.existsByPatientIdAndLabcorpIdAndBookingDateAndSlotType(
                patientToBook.getPatientId(),
                openSlot.getLabcorpId(),
                openSlot.getAvailabilityDate(),
                openSlot.getSlotType().toUpperCase()
        );

        if (bookingExists) {
            System.out.println("PROCESSOR: Booking already exists for Patient ID: " + patientToBook.getPatientId() + ". Skipping.");
            return null; // Return null to skip writing this item
        }

        System.out.println("PROCESSOR: Creating booking for Patient ID: " + patientToBook.getPatientId() + " at Lab ID: " + openSlot.getLabcorpId());

        Booking newBooking = new Booking();
        newBooking.setPatientId(patientToBook.getPatientId());
        newBooking.setLabcorpId(openSlot.getLabcorpId());
        newBooking.setBookingDate(openSlot.getAvailabilityDate());
        newBooking.setSlotType(openSlot.getSlotType().toUpperCase());

        return newBooking;
    }
}
