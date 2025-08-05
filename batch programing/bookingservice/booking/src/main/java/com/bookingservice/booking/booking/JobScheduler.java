package com.bookingservice.booking.booking;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This class handles the automated, scheduled execution of the batch job.
 */
@Component
@EnableScheduling // This annotation is crucial to enable scheduling
public class JobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job patientBookingJob; // Spring will inject the Job bean we define later

    /**
     * This method will be executed automatically based on the CRON expression.
     * CRON Expression: "0 0 22 ? * FRI" means:
     * - 0 seconds
     * - 0 minutes
     * - 22nd hour (10 PM)
     * - ? (any day of the month)
     * - * (any month)
     * - FRI (on Friday)
     */
    @Scheduled(cron = "0 0 22 ? * FRI")
    public void runPatientBookingJob() throws Exception {
        System.out.println("Starting scheduled patient booking job...");
        // JobParameters are needed to give each job run a unique identity.
        // Using the current time makes each run unique.
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(patientBookingJob, params);
        System.out.println("Scheduled patient booking job finished.");
    }
}
