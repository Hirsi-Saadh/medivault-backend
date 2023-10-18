package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.MedicalReport;
import com.codewithsaadh.medivaultbackend.repository.MedicalReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medical-reports")
public class MedicalReportController {

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    // Get all medical reports
    @GetMapping
    public List<MedicalReport> getAllMedicalReports() {
        return medicalReportRepository.findAll();
    }

    // Get a specific medical report by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalReport> getMedicalReportById(@PathVariable Long id) {
        Optional<MedicalReport> medicalReport = medicalReportRepository.findById(id);
        if (medicalReport.isPresent()) {
            return new ResponseEntity<>(medicalReport.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/add", consumes = "multipart/form-data")
    public ResponseEntity<?> createMedicalReport(
            @RequestPart(value = "medicalReport", required = false) String medicalReportJson,
            @RequestPart(value = "pdfFile", required = false) MultipartFile pdfFile) {
        try {
            System.out.println("Received request to create a medical report");

            if (medicalReportJson == null) {
                System.out.println("Medical Report data is missing");
                return new ResponseEntity<>("Medical Report data is missing", HttpStatus.BAD_REQUEST);
            }

            // Deserialize the JSON string to a MedicalReport object
            ObjectMapper objectMapper = new ObjectMapper();
            MedicalReport medicalReport = objectMapper.readValue(medicalReportJson, MedicalReport.class);

            System.out.println("Received Medical Report data: " + medicalReport);

            if (pdfFile != null && !pdfFile.isEmpty()) {
                byte[] pdfContent = pdfFile.getBytes();
                medicalReport.setPdfFile(pdfContent);
                medicalReport.setReportStatus("Report Received");
            } else if ("Report Received".equals(medicalReport.getReportStatus())) {
                System.out.println("PDF file is required for a received report");
                return new ResponseEntity<>("PDF file is required for a received report", HttpStatus.BAD_REQUEST);
            }

            System.out.println("Medical Report data after processing: " + medicalReport);
            String reportString = medicalReport.toString();
            System.out.println(reportString);

            MedicalReport savedReport = medicalReportRepository.save(medicalReport);
            System.out.println("Medical Report saved: " + savedReport);

            return new ResponseEntity<>(savedReport, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("An error occurred while creating the medical report: " + e.getMessage());
            return new ResponseEntity<>("An error occurred while creating the medical report: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }







    // Update an existing medical report
    @PutMapping("/{id}")
    public ResponseEntity<MedicalReport> updateMedicalReport(@PathVariable Long id, @RequestBody MedicalReport updatedReport) {
        Optional<MedicalReport> existingReport = medicalReportRepository.findById(id);
        if (existingReport.isPresent()) {
            MedicalReport report = existingReport.get();
            report.setReportName(updatedReport.getReportName());
            report.setReportTime(updatedReport.getReportTime());
            report.setPdfFile(updatedReport.getPdfFile());
            report.setReportStatus(updatedReport.getReportStatus());
            report.setPatientUid(updatedReport.getPatientUid());
            report.setLaboratoryUid(updatedReport.getLaboratoryUid());


            MedicalReport updatedReportEntity = medicalReportRepository.save(report);
            return new ResponseEntity<>(updatedReportEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a medical report by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalReport(@PathVariable Long id) {
        Optional<MedicalReport> medicalReport = medicalReportRepository.findById(id);
        if (medicalReport.isPresent()) {
            medicalReportRepository.delete(medicalReport.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    // Get medical reports by patient UID and laboratory UID
    @GetMapping("/by-patient-and-laboratory")
    public ResponseEntity<List<MedicalReport>> getMedicalReportsByPatientAndLaboratory(
            @RequestParam String patientUid,
            @RequestParam String laboratoryUid) {
        List<MedicalReport> reports = medicalReportRepository.findByPatientUidAndLaboratoryUid(patientUid, laboratoryUid);

        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
    }


    @GetMapping("/by-patient")
    public ResponseEntity<List<MedicalReport>> getMedicalReportsByPatient(@RequestParam String patientUid) {
        List<MedicalReport> reports = medicalReportRepository.findByPatientUid(patientUid);
        System.out.println("patient uid: "+patientUid);

        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
    }

    // Get medical reports by laboratory UID
    @GetMapping("/by-laboratory")
    public ResponseEntity<List<MedicalReport>> getMedicalReportsByLaboratory(@RequestParam String laboratoryUid) {
        List<MedicalReport> reports = medicalReportRepository.findByLaboratoryUid(laboratoryUid);

        if (reports.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
    }


}
