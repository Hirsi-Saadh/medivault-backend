package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository <Diagnosis, Long> {

    List<Diagnosis> findByPatientUid(String userUid);
}
