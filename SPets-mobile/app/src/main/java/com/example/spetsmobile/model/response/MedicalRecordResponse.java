package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

public class MedicalRecordResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("pet")
    private PetResponse pet;

    @SerializedName("hospital")
    private HospitalResponse hospital;

    @SerializedName("appointmentDate")
    private String appointmentDate;

    @SerializedName("diagnosis")
    private String diagnosis;

    @SerializedName("prescription")
    private String prescription;

    @SerializedName("notes")
    private String notes;

    public MedicalRecordResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetResponse getPet() {
        return pet;
    }

    public void setPet(PetResponse pet) {
        this.pet = pet;
    }

    public HospitalResponse getHospital() {
        return hospital;
    }

    public void setHospital(HospitalResponse hospital) {
        this.hospital = hospital;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
