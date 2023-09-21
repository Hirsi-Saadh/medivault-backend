package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.model.User;
import com.codewithsaadh.medivaultbackend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        try {
            System.out.println("Received patient data: " + patient.toString());

            Patient createdPatient = patientService.createPatient(
                    patient.getUid(),
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getAge(),
                    patient.getAddress(),
                    patient.getDateOfBirth()
            );
            return ResponseEntity.ok(createdPatient);
        } catch (Exception e) {
            // Handle any exceptions, e.g., validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        // Add other controller methods as needed
    }

    @GetMapping("/info")
    public ResponseEntity<Patient> getUserByUid(@RequestParam("uid") String uid) {
        Patient patient = patientService.findPatientByUid(uid);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}