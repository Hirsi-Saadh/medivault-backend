package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.model.Chaneling;
import com.codewithsaadh.medivaultbackend.repository.ChanelingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChanelingService {
    @Autowired
    private ChanelingRepository chanelingRepository;

    public Chaneling saveChaneling(Chaneling chaneling) {
        // You can perform any additional logic/validation here before saving
        return chanelingRepository.save(chaneling);
    }
}

