package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalReportRepository extends JpaRepository<com.codewithsaadh.medivaultbackend.model.MedicalReport, Long> {
    List<MedicalReport> findByPatientUidAndLaboratoryUid(String patientUid, String laboratoryUid);

    List<MedicalReport> findByPatientUid(String patientUid);

    List<MedicalReport> findByLaboratoryUid(String laboratoryUid);
}
