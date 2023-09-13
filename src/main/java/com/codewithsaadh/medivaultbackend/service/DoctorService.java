package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Doctor;

import com.codewithsaadh.medivaultbackend.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(String uid,
                               String doctorFirstName,
                               String doctorLastName,
                               String doctorAddress,
                               String doctorLicense,
                               String doctorType,
                               String doctorSpecialization,
                               byte[] doctorImageData,
                               byte[] doctorLicenseData) {
        try {
            Doctor doctor = new Doctor();
            doctor.setUid(uid);
            doctor.setDoctorFirstName(doctorFirstName);
            doctor.setDoctorLastName(doctorLastName);
            doctor.setDoctorAddress(doctorAddress);
            doctor.setDoctorLicense(doctorLicense);
            doctor.setDoctorType(doctorType);
            doctor.setDoctorSpecialization(doctorSpecialization);
            doctor.setDoctorImageBlob(doctorImageData);
            doctor.setDoctorLicenseBlob(doctorLicenseData);

            Doctor savedDoctor = doctorRepository.save(doctor);
            return savedDoctor;
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw e; // Re-throw the exception to see the exact error message
        }
    }

    public Doctor findDoctorByUid(String uid) {
        return doctorRepository.findByUid(uid);
    }
}
