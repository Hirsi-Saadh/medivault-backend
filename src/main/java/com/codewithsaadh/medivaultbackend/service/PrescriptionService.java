package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Prescription;
import com.codewithsaadh.medivaultbackend.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {



        @Autowired
        private PrescriptionRepository prescriptionRepository;

        public List<Prescription> savePrescriptions(List<Prescription> prescriptions) {
            // You can save the list of prescriptions in a batch operation
            return prescriptionRepository.saveAll(prescriptions);
        }


        public List<Prescription> getAllPrescriptionsByChanelingId(Long chanelingId) {
            // Call the repository to retrieve prescriptions based on the Chaneling ID
            return prescriptionRepository.findByChannelingUid(chanelingId);
        }

        public List<Prescription> getPrescriptionsByChannelingId(Long channelingId) {
            // Call the repository to retrieve prescriptions based on the Channeling ID
            return prescriptionRepository.findByChannelingUid(channelingId);
        }



}
