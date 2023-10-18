package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.repository.MedicalReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalReportService {

    @Autowired
    private MedicalReportRepository medicalReport;


    public List<com.codewithsaadh.medivaultbackend.model.MedicalReport> addMedicalReports(List<com.codewithsaadh.medivaultbackend.model.MedicalReport> medicalReportPrescriptions) {
        // Perform any additional validation or business logic here
        return medicalReport.saveAll(medicalReportPrescriptions);
    }

    public List<com.codewithsaadh.medivaultbackend.model.MedicalReport> getAllMedicalReports() {
        return medicalReport.findAll();
    }

    // Implement additional methods for updating, deleting, and retrieving individual medical reports as needed
}
