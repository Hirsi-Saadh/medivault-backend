package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Diagnosis;
import com.codewithsaadh.medivaultbackend.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    @Autowired
    private DiagnosisRepository diagnosisRepository;


    public List<Diagnosis> createDiagnosis(List<Diagnosis> diagnoses) {
        try {
            // Save the provided Diagnosis objects
            List<Diagnosis> savedDiagnosis = diagnosisRepository.saveAll(diagnoses);

            System.out.println("Saved Allergies: " + savedDiagnosis.toString());
            return savedDiagnosis;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    public List<Diagnosis> getDiagnosisForUser(String userUid) {
        // Implement logic to fetch all allergies associated with a user from the database
        // You may need to define a custom method in your repository or use query annotations
        return diagnosisRepository.findByPatientUid(userUid);
    }
}
