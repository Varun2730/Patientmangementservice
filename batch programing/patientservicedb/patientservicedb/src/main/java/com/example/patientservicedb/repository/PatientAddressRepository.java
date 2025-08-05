package com.example.patientservicedb.repository;

import com.example.patientservicedb.entity.PatientAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientAddressRepository extends JpaRepository<PatientAddress, UUID> {
}
