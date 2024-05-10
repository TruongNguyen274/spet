package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.HealthRecordSaveRequest;
import com.example.spetsrestapi.model.response.HealthRecordResponse;

import java.util.List;

public interface HealthRecordService {

    List<HealthRecordResponse> getAll();

    List<HealthRecordResponse> getByPet(Long petId);

    HealthRecordResponse save(HealthRecordSaveRequest request);

}
