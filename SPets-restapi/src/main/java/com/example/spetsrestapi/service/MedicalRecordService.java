package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.MedicalRecordSaveRequest;
import com.example.spetsrestapi.model.response.MedicalRecordResponse;

import java.util.List;

public interface MedicalRecordService {

    List<MedicalRecordResponse> getAllByPetOrderByUpdatedAtDesc(long petId);

    List<MedicalRecordResponse> getAllByHospitalOrderByUpdatedAtDesc(long hospitalId);

    MedicalRecordResponse save(MedicalRecordSaveRequest request);

}
