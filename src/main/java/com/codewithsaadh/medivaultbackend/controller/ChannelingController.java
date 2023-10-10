package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.Channeling;
import com.codewithsaadh.medivaultbackend.service.ChannelingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/channeling")
public class ChannelingController {

    private final ChannelingService channelingService;

    @Autowired
    public ChannelingController(ChannelingService channelingService) {
        this.channelingService = channelingService;
    }

    @GetMapping("/getChannelingRecords")
    public List<Channeling> getChannelingRecords(@RequestParam String patientUid) {
        // Call the service method to retrieve channeling records for a specific patient
        return channelingService.getChannelingRecordsByPatientUid(patientUid);
    }

}
