package com.bookingservice.booking.booking;



import com.bookingservice.booking.entity.Booking;
import com.bookingservice.booking.repository.BookingRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ItemWriter for the batch job.
 * It takes a list of new Booking objects and saves them to the database.
 */
@Component
public class SaveBookingsWriter implements ItemWriter<Booking> {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * This method is called after the processor has finished a "chunk" of items.
     * It writes all the items in the chunk to the database in a single transaction.
     */
    @Override
    public void write(List<? extends Booking> bookings) {
        System.out.println("WRITER: Saving a chunk of " + bookings.size() + " new bookings to the database.");
        bookingRepository.saveAll(bookings);
        System.out.println("WRITER: Chunk saved successfully.");

        // NOTE: In a real-world, production-ready system, this writer would also be responsible for
        // making an API call back to the Labcorp service to decrement the 'available_count'
        // for each of the slots that were just booked. This is a critical step to prevent
        // overbooking. For simplicity in this example, that API call is omitted.
    }
}
