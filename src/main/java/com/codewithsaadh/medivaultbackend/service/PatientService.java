package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(Patient patient) {
        // You can perform any necessary validations here before creating the patient
        String patientId = generatePatientId();
        patient.setPatientId(patientId);
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Long patientId, Patient updatedPatient) {
        // Retrieve the patient by ID from the database
        Optional<Patient> existingPatientOptional = patientRepository.findById(patientId);
        if (existingPatientOptional.isPresent()) {
            Patient existingPatient = existingPatientOptional.get();
            // Update the existing patient's properties with the values from the updatedPatient object
            existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
            existingPatient.setAddress(updatedPatient.getAddress());
            // Update any other properties as needed

            // Save the updated patient to the database
            return patientRepository.save(existingPatient);
        }
        return null;
    }

    public boolean deletePatient(Long patientId) {
        // Check if the patient exists in the database
        if (patientRepository.existsById(patientId)) {
            patientRepository.deleteById(patientId);
            return true;
        }
        return false;
    }
    // Other methods for updating and deleting patients can be added here

    // Additional business logic can be added as needed

    // Getters and Setters, if required


        public static String generatePatientId() {
            UUID uuid = UUID.randomUUID();
            // Remove hyphens and convert to uppercase to get a 32-character ID
            return uuid.toString().replace("-", "").toUpperCase();
        }

}

