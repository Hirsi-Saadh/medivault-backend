package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "laboratories")
@DiscriminatorValue("PHARMACY")
public class Laboratory {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String laboratoryName;

    @Column(nullable = false)
    private String laboratoryAddress;

    @Column(nullable = false)
    private String laboratoryLicense;

    @Column(nullable = false)
    private String laboratoryType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] laboratoryLicenseBlob;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getLaboratoryAddress() {
        return laboratoryAddress;
    }

    public void setLaboratoryAddress(String laboratoryAddress) {
        this.laboratoryAddress = laboratoryAddress;
    }

    public String getLaboratoryLicense() {
        return laboratoryLicense;
    }

    public void setLaboratoryLicense(String laboratoryLicense) {
        this.laboratoryLicense = laboratoryLicense;
    }

    public String getLaboratoryType() {
        return laboratoryType;
    }

    public void setLaboratoryType(String laboratoryType) {
        this.laboratoryType = laboratoryType;
    }

    public byte[] getLaboratoryLicenseBlob() {
        return laboratoryLicenseBlob;
    }

    public void setLaboratoryLicenseBlob(byte[] laboratoryLicenseBlob) {
        this.laboratoryLicenseBlob = laboratoryLicenseBlob;
    }

    public void setMedicalLicenseBlob(byte[] medicalLicenseData) {

    }
}

