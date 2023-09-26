package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Diagnosis;
import com.codewithsaadh.medivaultbackend.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/patients/diagnosis")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDiagnosis(@RequestBody List<Diagnosis> diagnoses) {
        try {
            // Log the received allergies for debugging
            System.out.println("Received diagnosis: " + diagnoses);

            // Perform any necessary validation and processing of allergies
            List<Diagnosis> createdDiagnosis = diagnosisService.createDiagnosis(diagnoses);

            // Return the created allergies in the response
            return ResponseEntity.ok(createdDiagnosis);
        } catch (Exception e) {
            // Handle exceptions gracefully and return a meaningful response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Diagnosis>> getDiagnosisForUser(@RequestParam("uid") String userUid) {
        try {
            // Query the database to retrieve all allergies for the specified user UID
            List<Diagnosis> diagnosis = diagnosisService.getDiagnosisForUser(userUid);

            // Return the list of allergies in the response
            return ResponseEntity.ok(diagnosis);
        } catch (Exception e) {
            // Handle exceptions gracefully and return a meaningful response
            List<Diagnosis> emptyList = Collections.emptyList();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList);
        }
    }
}
