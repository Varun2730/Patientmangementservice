package com.example.patientservicedb.service;

import com.example.patientservicedb.entity.PatientAddress;
import com.example.patientservicedb.entity.PatientInfo;
import com.example.patientservicedb.repository.PatientAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientAddressService {
    @Autowired
    private PatientAddressRepository patientAddressRepo;

    public List<PatientAddress> getpatientaddress() {
        return patientAddressRepo.findAll();

    }

}
