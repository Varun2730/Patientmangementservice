package com.example.patientservicedb.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "patientaddress", schema = "public")
public class PatientAddress {

    @Id
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID addressid;

    @Column(nullable = false)
    private Integer patientid;

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @Column(name = "createdat", insertable = false, updatable = false)
    private OffsetDateTime createdat;

    // --- Getters and Setters ---

    public UUID getAddressid() {
        return addressid;
    }

    public void setAddressid(UUID addressid) {
        this.addressid = addressid;
    }

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
