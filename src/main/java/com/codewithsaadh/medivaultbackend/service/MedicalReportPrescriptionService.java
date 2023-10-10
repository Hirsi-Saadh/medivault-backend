package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.MedicalReportPrescription;
import com.codewithsaadh.medivaultbackend.repository.MedicalReportPrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalReportPrescriptionService {

    @Autowired
    private MedicalReportPrescriptionRepository medicalReportPrescriptionRepository;


    public List<MedicalReportPrescription> addMedicalReports(List<MedicalReportPrescription> medicalReportPrescriptions) {
        // Perform any additional validation or business logic here
        return medicalReportPrescriptionRepository.saveAll(medicalReportPrescriptions);
    }

    public List<MedicalReportPrescription> getAllMedicalReports() {
        return medicalReportPrescriptionRepository.findAll();
    }

    // Implement additional methods for updating, deleting, and retrieving individual medical reports as needed
}
