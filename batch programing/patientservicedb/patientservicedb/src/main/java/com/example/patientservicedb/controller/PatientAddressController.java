package com.example.patientservicedb.controller;


import com.example.patientservicedb.entity.PatientAddress;
import com.example.patientservicedb.entity.PatientInfo;
import com.example.patientservicedb.repository.PatientAddressRepository;
import com.example.patientservicedb.repository.PatientInfoRepository;
import com.example.patientservicedb.service.PatientAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patientaddress")
public class PatientAddressController {
    @Autowired
    private PatientAddressRepository patientAddressRepo;
    @Autowired
    private PatientAddressService patientAddressService;

    @GetMapping
    public ResponseEntity<?> findAll()  {
        List<PatientAddress> padd = patientAddressService.getpatientaddress();
        return ResponseEntity.ok().body(padd);
    }


}
