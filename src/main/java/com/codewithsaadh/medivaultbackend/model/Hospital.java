package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "hospitals")
@DiscriminatorValue("HOSPITAL")
public class Hospital {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String hospitalName;

    @Column(nullable = false)
    private String hospitalAddress;

    @Column(nullable = false)
    private String hospitalLicense;

    @Column(nullable = false)
    private String hospitalType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] medicalLicenseBlob;

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

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getHospitalLicense() {
        return hospitalLicense;
    }

    public void setHospitalLicense(String hospitalLicense) {
        this.hospitalLicense = hospitalLicense;
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }

    // Getter and setter for medicalLicenseBlob

    public byte[] getMedicalLicenseBlob() {
        return medicalLicenseBlob;
    }

    public void setMedicalLicenseBlob(byte[] medicalLicenseBlob) {
        this.medicalLicenseBlob = medicalLicenseBlob;
    }


    @Override
    public String toString() {
        return "Hospital{" +
                "uid='" + uid + '\'' +
                ", Name='" + hospitalName + '\'' +
                ", Address=" + hospitalAddress +
                ", License=" + hospitalLicense +
                ", license image=" + medicalLicenseBlob +
                ", type'" + hospitalType + '\'' +
                '}';
    }
}






