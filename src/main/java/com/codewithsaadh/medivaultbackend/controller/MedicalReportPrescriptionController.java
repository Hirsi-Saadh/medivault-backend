package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.MedicalReportPrescription;
import com.codewithsaadh.medivaultbackend.service.MedicalReportPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-reports")
public class MedicalReportPrescriptionController {

    @Autowired
    private MedicalReportPrescriptionService medicalReportPrescriptionService;

    @PostMapping("/add")
    public ResponseEntity<List<MedicalReportPrescription>> addMedicalReports(@RequestBody List<MedicalReportPrescription> medicalReportPrescriptions) {
        try {
            // Validate the medical report data here

            List<MedicalReportPrescription> savedReports = medicalReportPrescriptionService.addMedicalReports(medicalReportPrescriptions);
            return new ResponseEntity<>(savedReports, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<MedicalReportPrescription>> getAllMedicalReports() {
        List<MedicalReportPrescription> medicalReports = medicalReportPrescriptionService.getAllMedicalReports();
        return new ResponseEntity<>(medicalReports, HttpStatus.OK);
    }

    // Add more endpoints for updating, deleting, and retrieving individual medical reports as needed
}

