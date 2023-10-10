package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Channeling;
import com.codewithsaadh.medivaultbackend.model.Prescription;
import com.codewithsaadh.medivaultbackend.service.ChannelingService;
import com.codewithsaadh.medivaultbackend.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private ChannelingService channelingService;
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/add")
    public ResponseEntity<String> saveCombinedData(@RequestBody List<Prescription> prescriptions) {
        try {
            // Get the doctorUid and patientUid from the first prescription (assuming it's available in the list)
            String doctorUid = prescriptions.get(0).getDoctorUid();
            String patientUid = prescriptions.get(0).getPatientUid();

            // Create and save the Chaneling object
            Channeling channeling = new Channeling();
            channeling.setPatientUid(patientUid);  // Set patientUid from the first prescription
            channeling.setDoctorUid(doctorUid);    // Set doctorUid from the first prescription
            Channeling savedChanneling = channelingService.saveChaneling(channeling);

            // Iterate through the prescriptions and set the channelingUid
            for (Prescription prescription : prescriptions) {
                prescription.setChannelingUid(savedChanneling.getId());
            }

            // Save the list of prescriptions in a batch operation
            prescriptionService.savePrescriptions(prescriptions);

            return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/view/{patientUid}")
    public ResponseEntity<List<Prescription>> getAllPrescriptionsForPatient(@PathVariable String patientUid) {

        System.out.println("Retrieved patient UID from the frontend: " + patientUid);

        try {
            // Call a service method to obtain all Chaneling IDs based on patientUid
            List<Long> chanelingIds = channelingService.getAllChanelingIdsByPatientUid(patientUid);
            System.out.println("Retrieved Chaneling IDs: " + chanelingIds);

            // Check if any Chaneling records were found for the patientUid
            if (chanelingIds.isEmpty()) {
                System.out.println("No Chaneling records found for patient UID: " + patientUid);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<Prescription> prescriptions = new ArrayList<>();

            // Iterate through each Chaneling ID and fetch prescriptions
            for (Long chanelingId : chanelingIds) {
                List<Prescription> prescriptionsForChaneling = prescriptionService.getAllPrescriptionsByChanelingId(chanelingId);
                prescriptions.addAll(prescriptionsForChaneling);
            }

            // Check if any prescriptions were found
            if (prescriptions.isEmpty()) {
                System.out.println("No prescriptions found for patient UID: " + patientUid);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            System.out.println("Retrieved prescriptions for patient UID: " + patientUid);

            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("An error occurred while processing the request for patient UID: " + patientUid);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/viewByChannelingId/{channelingId}")
    public ResponseEntity<List<Prescription>> getAllPrescriptionsByChannelingId(@PathVariable Long channelingId) {
        try {
            System.out.println("Received request to fetch prescriptions for channeling ID: " + channelingId);

            // Call the service method to obtain all prescriptions by channelingId
            List<Prescription> prescriptions = prescriptionService.getAllPrescriptionsByChanelingId(channelingId);

            // Check if any prescriptions were found
            if (prescriptions.isEmpty()) {
                System.out.println("No prescriptions found for channeling ID: " + channelingId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            System.out.println("Retrieved prescriptions for channeling ID: " + channelingId);

            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("An error occurred while processing the request for channeling ID: " + channelingId);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/history")
    public ResponseEntity<List<Channeling>> getChannelingHistory(
            @RequestParam String patientUid,
            @RequestParam String doctorUid
    ) {
        try {
            // Call a service method to obtain a list of channeling records based on patient UID and doctor UID
            List<Channeling> channelingHistory = channelingService.getChannelingRecordsByPatientUidAndDoctorUid(patientUid, doctorUid);

            if (channelingHistory.isEmpty()) {
                System.out.println("No channeling records found for patient UID: " + patientUid + " and doctor UID: " + doctorUid);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            System.out.println("Retrieved channeling history for patient UID: " + patientUid + " and doctor UID: " + doctorUid);

            return new ResponseEntity<>(channelingHistory, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("An error occurred while processing the request for patient UID: " + patientUid + " and doctor UID: " + doctorUid);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}


