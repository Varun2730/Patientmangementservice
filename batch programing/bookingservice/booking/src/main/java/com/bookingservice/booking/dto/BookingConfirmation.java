package com.bookingservice.booking.dto;
// dto/BookingConfirmation.java

import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for the final confirmation response sent back to the client
 * after a successful booking.
 */
public class BookingConfirmation {

    private UUID bookingId;
    private Integer patientId;
    private String patientName;
    private Integer labcorpId;
    private String labcorpName;
    private LocalDate bookingDate;
    private String slotType;

    // --- Getters and Setters ---

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getLabcorpId() {
        return labcorpId;
    }

    public void setLabcorpId(Integer labcorpId) {
        this.labcorpId = labcorpId;
    }

    public String getLabcorpName() {
        return labcorpName;
    }

    public void setLabcorpName(String labcorpName) {
        this.labcorpName = labcorpName;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }
}
