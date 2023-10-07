package com.codewithsaadh.medivaultbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private Long channelingUid;

    @Column(nullable = false)
    private String medicationName;

    @Column(nullable = false)
    private String dosage;

    @Column(nullable = false)
    private String medicationType;

    private String doctorUid;

    private String patientUid;

    public Prescription() {
    }

    public Prescription(Long channelingUid, String medicationName, String dosage, String medicationType) {
        this.channelingUid = channelingUid;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.medicationType = medicationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChannelingUid() {
        return channelingUid;
    }

    public void setChannelingUid(Long channelingUid) {
        this.channelingUid = channelingUid;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(String medicationType) {
        this.medicationType = medicationType;
    }


    public String getDoctorUid() {
        return doctorUid;
    }


    public String getPatientUid() {
        return patientUid;
    }

}

