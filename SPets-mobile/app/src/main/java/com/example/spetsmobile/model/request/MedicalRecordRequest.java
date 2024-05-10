package com.example.spetsmobile.model.request;

import com.example.spetsmobile.model.response.HospitalResponse;
import com.example.spetsmobile.model.response.PetResponse;
import com.google.gson.annotations.SerializedName;

public class MedicalRecordRequest {

    @SerializedName("id")
    private long id;

    @SerializedName("petId")
    private long petId;

    @SerializedName("hospitalId")
    private long hospitalId;

    @SerializedName("appointmentDate")
    private String appointmentDate;

    @SerializedName("diagnosis")
    private String diagnosis;

    @SerializedName("prescription")
    private String prescription;

    @SerializedName("notes")
    private String notes;

    public MedicalRecordRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(long hospitalId) {
        this.hospitalId = hospitalId;
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
