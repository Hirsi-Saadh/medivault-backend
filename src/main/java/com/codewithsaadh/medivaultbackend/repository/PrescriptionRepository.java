package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByChannelingUid(Long channelingUid);

    List<Prescription> findAllByChannelingUid(Long channelingId);
}
