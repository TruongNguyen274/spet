package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class MedicalRecordSaveRequest implements Serializable {

    private long id;

    @NotNull(message = "PetId is required!")
    private long petId;

    @NotNull(message = "HospitalId is required!")
    private long hospitalId;

    @NotBlank(message = "AppointmentDate is required!")
    private String appointmentDate;

    @NotBlank(message = "Diagnosis is required!")
    private String diagnosis;

    private String prescription;

    private String notes;
}
