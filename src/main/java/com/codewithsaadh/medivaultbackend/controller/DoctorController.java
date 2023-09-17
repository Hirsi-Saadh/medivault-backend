package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Doctor;
import com.codewithsaadh.medivaultbackend.model.Hospital;
import com.codewithsaadh.medivaultbackend.repository.DoctorRepository;
import com.codewithsaadh.medivaultbackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<Doctor> addDoctor(@ModelAttribute Doctor doctor,
                                            @RequestPart("doctorImageBase64") String doctorImageBase64,
                                            @RequestPart("doctorLicenseBase64") String doctorLicenseBase64) {
        try {
            // Check if the doctor object is null or has invalid data
            if (doctor == null || !validateDoctorData(doctor)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            System.out.println("Received doctor data: " + doctor.toString());
            System.out.println("Received doctor image: " + (doctorImageBase64 != null ? "available" : "not available"));
            System.out.println("Received doctor license: " + (doctorLicenseBase64 != null ? "available" : "not available"));


            // Save the medical license blob to your database or storage
            byte[] doctorImageData = Base64.getDecoder().decode(doctorImageBase64);
            byte[] doctorLicenseData = Base64.getDecoder().decode(doctorLicenseBase64);

            // Create the doctor with the medical license data
            Doctor createdDoctor = doctorService.createDoctor(
                    doctor.getUid(),
                    doctor.getDoctorFirstName(),
                    doctor.getDoctorLastName(),
                    doctor.getDoctorAddress(),
                    doctor.getDoctorLicense(),
                    doctor.getDoctorType(),
                    doctor.getDoctorSpecialization(),
                    doctorImageData,
                    doctorLicenseData
            );

            return ResponseEntity.ok(createdDoctor);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private boolean validateDoctorData(Doctor doctor) {
        // Implement your validation logic here
        // Return true if the hospital data is valid; otherwise, return false
        return true; // Modify this according to your validation rules
    }

    @GetMapping("/info")
    public ResponseEntity<Doctor> getDoctorByUid(@RequestParam("uid") String uid) {
        Doctor doctor = doctorService.findDoctorByUid(uid);
        if (doctor != null) {
            return ResponseEntity.ok(doctor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
