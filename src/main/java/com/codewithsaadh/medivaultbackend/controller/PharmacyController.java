package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Hospital;
import com.codewithsaadh.medivaultbackend.model.Pharmacy;
import com.codewithsaadh.medivaultbackend.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @Autowired
    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }


    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<Pharmacy> addPharmacy(@ModelAttribute Pharmacy pharmacy,
                                                @RequestPart("pharmacyLicenseBase64") String pharmacyLicenseBase64) {
        try {
            // Check if the pharmacy object is null or has invalid data
            if (pharmacy == null || !validatePharmacyData(pharmacy)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            System.out.println("Received pharmacy data: " + pharmacy.toString());
            System.out.println("Received pharmacy license: " + (pharmacyLicenseBase64 != null ? "available" : "not available"));


            // Save the medical license blob to your database or storage
            byte[] pharmacyLicenseData = Base64.getDecoder().decode(pharmacyLicenseBase64);

            // Create the pharmacy with the medical license data
            Pharmacy createdPharmacy = pharmacyService.createPharmacy(
                    pharmacy.getUid(),
                    pharmacy.getPharmacyName(),
                    pharmacy.getPharmacyAddress(),
                    pharmacy.getPharmacyLicense(),
                    pharmacy.getPharmacyType(),
                    pharmacyLicenseData
            );

            return ResponseEntity.ok(createdPharmacy);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean validatePharmacyData(Pharmacy pharmacy) {
        // Implement your validation logic here
        // Return true if the pharmacy data is valid; otherwise, return false
        return true; // Modify this according to your validation rules
    }


    @GetMapping("/info")
    public ResponseEntity<Pharmacy> getPharmacyByUid(@RequestParam("uid") String uid) {
        Pharmacy pharmacy = pharmacyService.findPharmacyByUid(uid);
        if (pharmacy != null) {
            return ResponseEntity.ok(pharmacy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
