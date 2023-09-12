package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

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
    private Blob medicalLicenseBlob;

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
    public Blob getMedicalLicenseBlob() {
        return medicalLicenseBlob;
    }

    public void setMedicalLicenseBlob(Blob medicalLicenseBlob) {
        this.medicalLicenseBlob = medicalLicenseBlob;
    }

    // Save the image to the database
    public void saveMedicalLicense(MultipartFile file) throws IOException, SQLException {
        byte[] imageBytes = file.getBytes();
        Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
        this.medicalLicenseBlob = imageBlob;
    }

    // Retrieve the image as a byte array
    public byte[] getMedicalLicense() throws SQLException {
        if (this.medicalLicenseBlob != null) {
            int blobLength = (int) this.medicalLicenseBlob.length();
            return this.medicalLicenseBlob.getBytes(1, blobLength);
        } else {
            return null;
        }
    }

    {

    }
}



