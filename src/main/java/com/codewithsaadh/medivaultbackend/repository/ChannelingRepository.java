package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.Channeling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelingRepository extends JpaRepository<Channeling, Long> {
    List<Channeling> findAllByPatientUid(@Param("patient_uid") String patientUid);

    List<Channeling> findByPatientUid(String patientUid);

    List<Channeling> findByPatientUidAndDoctorUid(String patientUid, String doctorUid);
}

