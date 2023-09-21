package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.service.AllergyService;
import com.codewithsaadh.medivaultbackend.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/patients/allergy")
public class AllergyController {

    private final AllergyService allergyService;
    @Autowired
    public AllergyController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAllergies(@RequestBody List<Allergy> allergies) {
        try {
            // Log the received allergies for debugging
            System.out.println("Received allergies: " + allergies);

            // Perform any necessary validation and processing of allergies
            List<Allergy> createdAllergies = allergyService.createAllergies(allergies);

            // Return the created allergies in the response
            return ResponseEntity.ok(createdAllergies);
        } catch (Exception e) {
            // Handle exceptions gracefully and return a meaningful response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Allergy>> getAllergiesForUser(@RequestParam("uid") String userUid) {
        try {
            // Query the database to retrieve all allergies for the specified user UID
            List<Allergy> allergies = allergyService.getAllergiesForUser(userUid);

            // Return the list of allergies in the response
            return ResponseEntity.ok(allergies);
        } catch (Exception e) {
            // Handle exceptions gracefully and return a meaningful response
            List<Allergy> emptyList = Collections.emptyList();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList);
        }
    }





}