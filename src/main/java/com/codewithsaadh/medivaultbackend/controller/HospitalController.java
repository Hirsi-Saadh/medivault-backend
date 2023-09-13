package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Hospital;
import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }


    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<Hospital> addHospital(@ModelAttribute Hospital hospital,
                                         @RequestPart("medicalLicenseBase64") String medicalLicenseBase64) {
        try {
            // Check if the hospital object is null or has invalid data
            if (hospital == null || !validateHospitalData(hospital)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            System.out.println("Received hospital data: " + hospital.toString());
            System.out.println("Received hospital license: " + (medicalLicenseBase64 != null ? "available" : "not available"));


            // Save the medical license blob to your database or storage
            byte[] medicalLicenseData = Base64.getDecoder().decode(medicalLicenseBase64);

            // Create the hospital with the medical license data
            Hospital createdHospital = hospitalService.createHospital(
                    hospital.getUid(),
                    hospital.getHospitalName(),
                    hospital.getHospitalAddress(),
                    hospital.getHospitalLicense(),
                    hospital.getHospitalType(),
                    medicalLicenseData
            );

            return ResponseEntity.ok(createdHospital);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean validateHospitalData(Hospital hospital) {
        // Implement your validation logic here
        // Return true if the hospital data is valid; otherwise, return false
        return true; // Modify this according to your validation rules
    }


    @GetMapping("/info")
    public ResponseEntity<Hospital> getHospitalByUid(@RequestParam("uid") String uid) {
        Hospital hospital = hospitalService.findHospitalByUid(uid);
        if (hospital != null) {
            return ResponseEntity.ok(hospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
