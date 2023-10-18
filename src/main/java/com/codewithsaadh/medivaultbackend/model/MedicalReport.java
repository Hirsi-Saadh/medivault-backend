package com.codewithsaadh.medivaultbackend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "medical_reports")
public class MedicalReport {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reportName;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private String reportTime;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] pdfFile;

    @Column(nullable = false)
    private String reportStatus;

    @Column(nullable = false)
    private String patientUid;

    @Column(nullable = false)
    private String laboratoryUid;

    public MedicalReport() {
        this.reportStatus = "Report Not Yet Received"; // Set initial status
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
        this.reportStatus = "Report Received"; // Update status when PDF file is set
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    public String getLaboratoryUid() {
        return laboratoryUid;
    }

    public void setLaboratoryUid(String laboratoryUid) {
        this.laboratoryUid = laboratoryUid;
    }

    @Override
    public String toString() {
        return "MedicalReport{" +
                "id=" + id +
                ", reportName='" + reportName + '\'' +
                ", reportTime='" + reportTime + '\'' +
                ", reportStatus='" + reportStatus + '\'' +
                ", patientUid='" + patientUid + '\'' +
                ", laboratoryUid='" + laboratoryUid + '\'' +
                '}';
    }

}
