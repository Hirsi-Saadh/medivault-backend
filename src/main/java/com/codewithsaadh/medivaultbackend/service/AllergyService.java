package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.repository.AllergyRepository;
import com.codewithsaadh.medivaultbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AllergyService {


    @Autowired
    private AllergyRepository allergyRepository;


    public List<Allergy> createAllergies(List<Allergy> allergies) {
        try {
            // Save the provided Allergy objects
            List<Allergy> savedAllergies = allergyRepository.saveAll(allergies);

            System.out.println("Saved Allergies: " + savedAllergies.toString());
            return savedAllergies;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public List<Allergy> getAllergiesForUser(String userUid) {
        // Implement logic to fetch all allergies associated with a user from the database
        // You may need to define a custom method in your repository or use query annotations
        return allergyRepository.findBypatientUid(userUid);
    }





}

