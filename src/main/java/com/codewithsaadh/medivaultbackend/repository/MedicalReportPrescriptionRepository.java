package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.MedicalReportPrescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalReportPrescriptionRepository extends JpaRepository<MedicalReportPrescription, Long> {
}
