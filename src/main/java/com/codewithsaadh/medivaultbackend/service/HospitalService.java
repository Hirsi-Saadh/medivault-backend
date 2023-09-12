package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Hospital;
import com.codewithsaadh.medivaultbackend.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Blob;
import java.time.LocalDate;
@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Hospital createHospital(String uid, String hospitalName, String hospitalAddress,
                                   String hospitalLicense, String hospitalType, Blob medicalLicenseBlob) {
        try {
            Hospital hospital = new Hospital();
            hospital.setUid(uid);
            hospital.setHospitalName(hospitalName);
            hospital.setHospitalAddress(hospitalAddress);
            hospital.setHospitalLicense(hospitalLicense);
            hospital.setHospitalType(hospitalType);
            hospital.setMedicalLicenseBlob(medicalLicenseBlob);

            Hospital savedHospital = hospitalRepository.save(hospital);
            return savedHospital;
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw e; // Re-throw the exception to see the exact error message
        }
    }

}
