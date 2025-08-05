package com.bookingservice.booking.booking;



import com.bookingservice.booking.dto.LabcorpAvailabilityDTO;
import com.bookingservice.booking.dto.LabcorpDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ItemReader for the batch job.
 * It reads all available lab slots by calling the Labcorp microservice.
 */
@Component
public class FindOpenSlotsReader implements ItemReader<LabcorpAvailabilityDTO> {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${labcorp.service.url}")
    private String labcorpServiceUrl;

    private Queue<LabcorpAvailabilityDTO> openSlotsQueue;

    /**
     * This method is called once at the beginning of the step to fetch all the data.
     */
    private void fetchOpenSlots() {
        System.out.println("READER: Fetching all labs and their availability...");
        LabcorpDTO[] allLabs = restTemplate.getForObject(labcorpServiceUrl, LabcorpDTO[].class);

        this.openSlotsQueue = new LinkedList<>();
        if (allLabs != null) {
            // Flatten the structure: create a single list of all open slots from all labs
            for (LabcorpDTO lab : allLabs) {
                if (lab.getAvailabilities() != null) {
                    for (LabcorpAvailabilityDTO availability : lab.getAvailabilities()) {
                        // Add a slot to the queue for each available count
                        for (int i = 0; i < availability.getAvailableCount(); i++) {
                            // We need to associate the lab ID with the availability for the processor
                            availability.setLabcorpId(lab.getLabcorpId());
                            openSlotsQueue.add(availability);
                        }
                    }
                }
            }
        }
        System.out.println("READER: Found " + openSlotsQueue.size() + " total open appointment slots.");
    }

    /**
     * This method is called repeatedly by Spring Batch until it returns null.
     * Each call should return one item (one open slot).
     */
    @Override
    public LabcorpAvailabilityDTO read() {
        // Fetch the data only once when the reader is first called
        if (openSlotsQueue == null) {
            fetchOpenSlots();
        }

        // Return one item from the queue at a time
        return openSlotsQueue.poll(); // poll() returns null if the queue is empty
    }
}
