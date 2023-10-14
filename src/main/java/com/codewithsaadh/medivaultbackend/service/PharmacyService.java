package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Pharmacy;
import com.codewithsaadh.medivaultbackend.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    public PharmacyService(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public Pharmacy createPharmacy(String uid, String pharmacyName, String pharmacyAddress,
                                   String pharmacyLicense, String pharmacyType, byte[] pharmacyLicenseData) {
        try {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setUid(uid);
            pharmacy.setPharmacyName(pharmacyName);
            pharmacy.setPharmacyAddress(pharmacyAddress);
            pharmacy.setPharmacyLicense(pharmacyLicense);
            pharmacy.setPharmacyType(pharmacyType);
            pharmacy.setPharmacyLicenseBlob(pharmacyLicenseData);

            Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);
            return savedPharmacy;
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging
            throw e; // Re-throw the exception to see the exact error message
        }
    }

    public Pharmacy findPharmacyByUid(String uid) {
        return pharmacyRepository.findByUid(uid);
    }
}
