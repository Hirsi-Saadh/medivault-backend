package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Channeling;
import com.codewithsaadh.medivaultbackend.model.Pharmacy;
import com.codewithsaadh.medivaultbackend.model.Prescription;
import com.codewithsaadh.medivaultbackend.repository.ChannelingRepository;
import com.codewithsaadh.medivaultbackend.repository.PharmacyRepository;
import com.codewithsaadh.medivaultbackend.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ChannelingRepository channelingRepository;

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




    public List<Prescription> getAllPrescriptionsByChannelingId(Long channelingId) {
        // You can use the prescription repository to fetch all prescriptions by channelingId
        return prescriptionRepository.findAllByChannelingUid(channelingId);
    }


    public Channeling getChannelingById(Long channelingId) {
        // You can use the channeling repository to fetch the channeling data by ID
        Optional<Channeling> optionalChanneling = channelingRepository.findById(channelingId);

        if (optionalChanneling.isPresent()) {
            return optionalChanneling.get();
        } else {
            return null; // Or throw an exception or handle it according to your needs
        }
    }

}
