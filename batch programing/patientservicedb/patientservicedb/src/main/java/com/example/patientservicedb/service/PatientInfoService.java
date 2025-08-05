package com.example.patientservicedb.service;

import com.example.patientservicedb.entity.PatientInfo;
import com.example.patientservicedb.repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientInfoService {
    @Autowired
    private PatientInfoRepository  patientInfoRepo;

    public List<PatientInfo> getallpatientsinfo(){
return patientInfoRepo.findAll();    }


}
