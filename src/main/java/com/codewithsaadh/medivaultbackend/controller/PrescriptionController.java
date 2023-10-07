package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Chaneling;
import com.codewithsaadh.medivaultbackend.model.Prescription;
import com.codewithsaadh.medivaultbackend.service.AllergyService;
import com.codewithsaadh.medivaultbackend.service.ChanelingService;
import com.codewithsaadh.medivaultbackend.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {



    @Autowired
    private ChanelingService chanelingService;
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/add")
    public ResponseEntity<String> saveCombinedData(@RequestBody List<Prescription> prescriptions) {
        try {
            // Get the doctorUid and patientUid from the first prescription (assuming it's available in the list)
            String doctorUid = prescriptions.get(0).getDoctorUid();
            String patientUid = prescriptions.get(0).getPatientUid();

            // Create and save the Chaneling object
            Chaneling chaneling = new Chaneling();
            chaneling.setPatientUid(patientUid);  // Set patientUid from the first prescription
            chaneling.setDoctorUid(doctorUid);    // Set doctorUid from the first prescription
            Chaneling savedChaneling = chanelingService.saveChaneling(chaneling);

            // Iterate through the prescriptions and set the channelingUid
            for (Prescription prescription : prescriptions) {
                prescription.setChannelingUid(savedChaneling.getId());
            }

            // Save the list of prescriptions in a batch operation
            prescriptionService.savePrescriptions(prescriptions);

            return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
