package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Laboratory;
import com.codewithsaadh.medivaultbackend.repository.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    public LaboratoryService(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    public Laboratory createLaboratory(String uid, String laboratoryName, String laboratoryAddress,
                                   String laboratoryLicense, String laboratoryType, byte[] medicalLicenseData) {
        try {
            Laboratory laboratory = new Laboratory();
            laboratory.setUid(uid);
            laboratory.setLaboratoryName(laboratoryName);
            laboratory.setLaboratoryAddress(laboratoryAddress);
            laboratory.setLaboratoryLicense(laboratoryLicense);
            laboratory.setLaboratoryType(laboratoryType);
            laboratory.setMedicalLicenseBlob(medicalLicenseData);

            Laboratory savedLaboratory = laboratoryRepository.save(laboratory);
            return savedLaboratory;
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw e; // Re-throw the exception to see the exact error message
        }
    }

    public Laboratory findLaboratoryByUid(String uid) {
        return laboratoryRepository.findByUid(uid);
    }





}
