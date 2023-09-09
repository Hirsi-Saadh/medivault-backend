package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.model.User;
import com.codewithsaadh.medivaultbackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient createPatient(String uid, String firstName, String lastName, int age, String address, LocalDate dateOfBirth) {
        try {
            System.out.println("Starting to add to database");
            Patient patient = new Patient();
            patient.setUid(uid);
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setAge(age);
            patient.setAddress(address);
            patient.setDateOfBirth(dateOfBirth);

            // You can set other fields as needed

            Patient savedPatient = patientRepository.save(patient);
            System.out.println("Saved patient: " + savedPatient.toString());

            return savedPatient;
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw e; // Re-throw the exception to see the exact error message
        }
    }

    public Patient findPatientByUid(String uid) {
        return patientRepository.findByUid(uid);
    }



}

