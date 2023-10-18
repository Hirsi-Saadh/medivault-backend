package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Laboratory;
import com.codewithsaadh.medivaultbackend.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/laboratory")
public class LaboratoryController {

    private final LaboratoryService laboratoryService;

    @Autowired
    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }


    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<Laboratory> addLaboratory(@ModelAttribute Laboratory laboratory,
                                         @RequestPart("medicalLicenseBase64") String medicalLicenseBase64) {
        try {
            // Check if the laboratory object is null or has invalid data
            if (laboratory == null || !validateLaboratoryData(laboratory)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            System.out.println("Received laboratory data: " + laboratory.toString());
            System.out.println("Received laboratory license: " + (medicalLicenseBase64 != null ? "available" : "not available"));


            // Save the medical license blob to your database or storage
            byte[] medicalLicenseData = Base64.getDecoder().decode(medicalLicenseBase64);

            // Create the laboratory with the medical license data
            Laboratory createdLaboratory = laboratoryService.createLaboratory(
                    laboratory.getUid(),
                    laboratory.getLaboratoryName(),
                    laboratory.getLaboratoryAddress(),
                    laboratory.getLaboratoryLicense(),
                    laboratory.getLaboratoryType(),
                    medicalLicenseData
            );

            return ResponseEntity.ok(createdLaboratory);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean validateLaboratoryData(Laboratory laboratory) {
        // Implement your validation logic here
        // Return true if the laboratory data is valid; otherwise, return false
        return true; // Modify this according to your validation rules
    }


    @GetMapping("/info")
    public ResponseEntity<Laboratory> getLaboratoryByUid(@RequestParam("uid") String uid) {
        Laboratory laboratory = laboratoryService.findLaboratoryByUid(uid);
        if (laboratory != null) {
            return ResponseEntity.ok(laboratory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
