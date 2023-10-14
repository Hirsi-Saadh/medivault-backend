package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "pharmacies")
@DiscriminatorValue("PHARMACY")
public class Pharmacy {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String pharmacyName;

    @Column(nullable = false)
    private String pharmacyAddress;

    @Column(nullable = false)
    private String pharmacyLicense;

    @Column(nullable = false)
    private String pharmacyType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] pharmacyLicenseBlob;

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

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyAddress() {
        return pharmacyAddress;
    }

    public void setPharmacyAddress(String pharmacyAddress) {
        this.pharmacyAddress = pharmacyAddress;
    }

    public String getPharmacyLicense() {
        return pharmacyLicense;
    }

    public void setPharmacyLicense(String pharmacyLicense) {
        this.pharmacyLicense = pharmacyLicense;
    }

    public String getPharmacyType() {
        return pharmacyType;
    }

    public void setPharmacyType(String pharmacyType) {
        this.pharmacyType = pharmacyType;
    }

    public byte[] getPharmacyLicenseBlob() {
        return pharmacyLicenseBlob;
    }

    public void setPharmacyLicenseBlob(byte[] pharmacyLicenseBlob) {
        this.pharmacyLicenseBlob = pharmacyLicenseBlob;
    }
}

