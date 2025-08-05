package com.bookingservice.booking.booking;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This REST controller provides an endpoint to trigger the batch job on-demand.
 */
@RestController
@RequestMapping("/api/batch")
public class JobTriggerController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired

    private Job patientBookingJob;

    /**
     * POST /api/batch/run-booking-job
     * Manually starts the patient booking batch job.
     * @return A response entity indicating the job has been started.
     */
    @PostMapping("/run-booking-job")
    public ResponseEntity<String> triggerJob() {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(patientBookingJob, params);
            return ResponseEntity.ok("Patient booking job started successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error starting patient booking job: " + e.getMessage());
        }
    }
}
