package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "allergies")
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Id
    private Long allergyId;

    @Column(nullable = false)
    private String patientUid;

    @Column(nullable = false)
    private String allergyName;

    @Column(nullable = false)
    private String allergyDescription;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Other fields related to the allergy (e.g., description, dateDiagnosed, etc.)

    // Getter and setter methods


    public Long getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Long allergyId) {
        this.allergyId = allergyId;
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public String getAllergyDescription() {
        return allergyDescription;
    }

    public void setAllergyDescription(String allergyDescription) {
        this.allergyDescription = allergyDescription;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "allergyId=" + allergyId +
                ", patient=" + patientUid +
                ", allergyName='" + allergyName + '\'' +
                ", allergyDescription='" + allergyDescription + '\'' +
                '}';
    }


}


