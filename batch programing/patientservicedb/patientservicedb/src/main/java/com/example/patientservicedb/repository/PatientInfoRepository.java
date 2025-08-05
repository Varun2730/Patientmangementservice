package com.example.patientservicedb.repository;

import com.example.patientservicedb.entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientInfoRepository extends JpaRepository<PatientInfo, Integer> {
}
