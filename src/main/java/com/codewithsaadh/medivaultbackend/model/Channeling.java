package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "channeling")
public class Channeling {

    @Id
    @GeneratedValue(generator = "custom-id-generator")
    @GenericGenerator(name = "custom-id-generator", strategy = "com.codewithsaadh.medivaultbackend.configurations.CustomIDGenerator")
    private Long id;

    @Column(nullable = false)
    private String patientUid;

    @Column(nullable = false)
    private String doctorUid;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "added_time", nullable = false)
    private Date addedTime;

    public Channeling(Long id, String patientUid, String doctorUid) {
        this.id = id;
        this.patientUid = patientUid;
        this.doctorUid = doctorUid;
    }

    public Channeling() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    public String getDoctorUid() {
        return doctorUid;
    }

    public void setDoctorUid(String doctorUid) {
        this.doctorUid = doctorUid;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }
}
