package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Hospital;
import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }


    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<Hospital> addHospital(@RequestPart("formData") Hospital hospital,
                                                @RequestPart("medicalLicenseBlob") MultipartFile medicalLicenseBlob) {
        try {
            System.out.println("Received patient data: " + hospital.toString());

            Hospital createdHospital = hospitalService.createHospital(
                    hospital.getUid(),
                    hospital.getHospitalName(),
                    hospital.getHospitalAddress(),
                    hospital.getHospitalLicense(),
                    hospital.getHospitalType(),
                    hospital.getMedicalLicenseBlob()
            );
            return ResponseEntity.ok(createdHospital);
        } catch (Exception e) {
            // Handle any exceptions, e.g., validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
