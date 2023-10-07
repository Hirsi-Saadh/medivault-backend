package com.codewithsaadh.medivaultbackend.repository;

import com.codewithsaadh.medivaultbackend.model.Allergy;
import com.codewithsaadh.medivaultbackend.model.Chaneling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChanelingRepository extends JpaRepository<Chaneling, Long> {
}
