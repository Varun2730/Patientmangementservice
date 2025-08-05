package com.bookingservice.booking.booking;



import com.bookingservice.booking.dto.LabcorpAvailabilityDTO;
import com.bookingservice.booking.entity.Booking;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class configures the Spring Batch job for automatically booking patients.
 */
@Configuration
@EnableBatchProcessing // Essential for enabling Spring Batch features
public class PatientBookingJobConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private FindOpenSlotsReader findOpenSlotsReader;

    @Autowired
    private com.bookingservice.booking.batch.BookNextPatientProcessor bookNextPatientProcessor;

    @Autowired
    private SaveBookingsWriter saveBookingsWriter;

    /**
     * Defines the main batch job. A job is composed of one or more steps.
     * @return The configured Job bean.
     */
    @Bean
    public Job patientBookingJob() {
        return jobBuilderFactory.get("patientBookingJob")
                .incrementer(new RunIdIncrementer()) // Ensures each job run has a unique ID
                .flow(bookingStep())
                .end()
                .build();
    }

    /**
     * Defines the single step for our booking job.
     * This step reads open slots, processes them to create bookings, and writes them to the database.
     * @return The configured Step bean.
     */
    @Bean
    public Step bookingStep() {
        return stepBuilderFactory.get("bookingStep")
                // <InputType, OutputType>
                .<LabcorpAvailabilityDTO, Booking>chunk(10) // Process records in chunks of 10
                .reader(findOpenSlotsReader)
                .processor(bookNextPatientProcessor)
                .writer(saveBookingsWriter)
                .build();
    }
}
