package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "doctors")
@DiscriminatorValue("DOCTOR")
public class Doctor {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;

    @Column(nullable = false)
    private String doctorFirstName;

    @Column(nullable = false)
    private String doctorLastName;

    @Column(nullable = false)
    private String doctorAddress;

    @Column(nullable = false)
    private String doctorLicense;

    @Column(nullable = false)
    private String doctorType;

    @Column(nullable = false)
    private String doctorSpecialization;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] doctorImageBlob;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] doctorLicenseBlob;

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

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getDoctorLicense() {
        return doctorLicense;
    }

    public void setDoctorLicense(String doctorLicense) {
        this.doctorLicense = doctorLicense;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public byte[] getDoctorImageBlob() {
        return doctorImageBlob;
    }

    public void setDoctorImageBlob(byte[] doctorImageBlob) {
        this.doctorImageBlob = doctorImageBlob;
    }

    public byte[] getDoctorLicenseBlob() {
        return doctorLicenseBlob;
    }

    public void setDoctorLicenseBlob(byte[] doctorLicenseBlob) {
        this.doctorLicenseBlob = doctorLicenseBlob;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "uid='" + uid + '\'' +
                ", First Name='" + doctorFirstName + '\'' +
                ", Last Name='" + doctorLastName + '\'' +
                ", Address=" + doctorAddress +
                ", License=" + doctorLicense +
                ", license image=" + doctorImageBlob +
                ", license image=" + doctorLicenseBlob +
                ", type'" + doctorType + '\'' +
                '}';
    }


}

