package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Patient;
import com.codewithsaadh.medivaultbackend.model.Prescription;
import com.codewithsaadh.medivaultbackend.repository.PatientRepository;
import com.codewithsaadh.medivaultbackend.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {


        @Autowired
        private PrescriptionRepository prescriptionRepository;

        @Autowired
        private PatientRepository patientRepository;

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

    public Patient getPatientDetailsByUid(String patientUid) {
        return patientRepository.findByUid(patientUid);
    }

    public List<Prescription> getAllPrescriptionsByChannelingId(Long channelingId) {
        return prescriptionRepository.findByChannelingUid(channelingId);
    }

    public List<Prescription> getPrescriptionsByIds(List<Long> prescriptionIds) {
        return prescriptionRepository.findAllById(prescriptionIds);
    }



}
