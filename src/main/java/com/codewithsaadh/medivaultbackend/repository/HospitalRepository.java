package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository <Hospital, Long> {


}
