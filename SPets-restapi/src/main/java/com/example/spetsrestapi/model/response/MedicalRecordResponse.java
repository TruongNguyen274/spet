package com.example.spetsrestapi.model.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MedicalRecordResponse extends BaseResponse implements Serializable {

    private long id;
    private PetResponse pet;
    private HospitalResponse hospital;
    private String appointmentDate;
    private String diagnosis;
    private String prescription;
    private String notes;

}
