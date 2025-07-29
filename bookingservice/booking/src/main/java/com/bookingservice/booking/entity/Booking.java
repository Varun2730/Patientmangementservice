package com.bookingservice.booking.entity;


//import jakarta.persistence.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a booking record in the database.
 * This entity maps to the 'booking' table.
 */
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "booking_id", updatable = false, nullable = false)
    private UUID bookingId;

    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(name = "labcorp_id", nullable = false)
    private Integer labcorpId;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "slot_type", nullable = false)
    private String slotType; // e.g., 'MORNING', 'AFTERNOON', 'EVENING'

    @Column(name = "booked_at", nullable = false, updatable = false, insertable = false)
    private OffsetDateTime bookedAt;

    // --- Constructors ---

    public Booking() {
    }

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

    public Integer getLabcorpId() {
        return labcorpId;
    }

    public void setLabcorpId(Integer labcorpId) {
        this.labcorpId = labcorpId;
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

    public OffsetDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(OffsetDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    // --- equals, hashCode, and toString ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", patientId=" + patientId +
                ", labcorpId=" + labcorpId +
                ", bookingDate=" + bookingDate +
                ", slotType='" + slotType + '\'' +
                ", bookedAt=" + bookedAt +
                '}';
    }
}
