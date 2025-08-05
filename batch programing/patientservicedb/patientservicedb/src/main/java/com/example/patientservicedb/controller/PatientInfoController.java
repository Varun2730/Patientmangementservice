package com.example.patientservicedb.controller;

import com.example.patientservicedb.entity.PatientInfo;
import com.example.patientservicedb.service.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
// --- THIS IS THE CORRECTED LINE ---
@RequestMapping("/api/patients")
public class PatientInfoController {
    @Autowired
    PatientInfoService patientinfser;

    @GetMapping
    public ResponseEntity<?> findAll()  {
        List<PatientInfo> pinf = patientinfser.getallpatientsinfo();
        return ResponseEntity.ok().body(pinf);
    }
}