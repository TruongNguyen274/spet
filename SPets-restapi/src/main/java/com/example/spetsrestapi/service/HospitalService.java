package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.entity.Hospital;
import com.example.spetsrestapi.model.request.HospitalSaveRequest;
import com.example.spetsrestapi.model.response.HospitalResponse;

import java.util.List;

public interface HospitalService {

    List<HospitalResponse> getHospital();

    HospitalResponse getHospitalByAccountId(long accountId);

    HospitalResponse save(HospitalSaveRequest request);

    Hospital findById(long hospitalId);

    Hospital save(Hospital hospital);

}
