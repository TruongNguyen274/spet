package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.entity.Symptom;
import com.example.spetsrestapi.model.response.SymptomResponse;

import java.util.List;

public interface SymptomService {

    List<SymptomResponse> findByActive();

    List<SymptomResponse> findSymptomByIdIn(List<Long> ids);

}
