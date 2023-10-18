package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Channeling;
import com.codewithsaadh.medivaultbackend.model.Doctor;
import com.codewithsaadh.medivaultbackend.repository.ChannelingRepository;
import com.codewithsaadh.medivaultbackend.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelingService {
    @Autowired
    private ChannelingRepository channelingRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Channeling saveChaneling(Channeling channeling) {
        // You can perform any additional logic/validation here before saving
        return channelingRepository.save(channeling);
    }

    public List<Long> getAllChanelingIdsByPatientUid(String patientUid) {
        // Call the repository to retrieve all Chaneling IDs based on the patientUid
        List<Channeling> channelings = channelingRepository.findAllByPatientUid(patientUid);

        List<Long> chanelingIds = new ArrayList<>();

        // Iterate through the found Chaneling records and extract their IDs
        for (Channeling channeling : channelings) {
            chanelingIds.add(channeling.getId());
        }

        return chanelingIds;
    }

    public List<Channeling> getChannelingRecordsByPatientUid(String patientUid) {
        List<Channeling> channelingRecords = channelingRepository.findByPatientUid(patientUid);

        // Fetch doctor names for each channeling record
        for (Channeling channeling : channelingRecords) {
            String doctorUid = channeling.getDoctorUid();
            Doctor doctor = doctorRepository.findByUid(doctorUid);
            if (doctor != null) {
                channeling.setDoctorFirstName(doctor.getDoctorFirstName()); // Assuming you have a 'name' property in the Doctor class
                channeling.setDoctorLastName(doctor.getDoctorLastName());
            }
        }

        return channelingRecords;
    }


    public List<Channeling> getChannelingRecordsByPatientUidAndDoctorUid(String patientUid, String doctorUid) {


        return channelingRepository.findByPatientUidAndDoctorUid(patientUid, doctorUid);
    }








}

