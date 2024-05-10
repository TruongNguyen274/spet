package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Symptom;
import com.example.spetsrestapi.model.response.SymptomResponse;
import com.example.spetsrestapi.repository.SymptomRepository;
import com.example.spetsrestapi.service.SymptomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SymptomResponse> findByActive() {
        List<SymptomResponse> responses = new ArrayList<>();
        List<Symptom> symptomList = symptomRepository.findByStatusIsTrue();
        for (Symptom symptom : symptomList) {
            SymptomResponse symptomResponse = new SymptomResponse();
            symptomResponse.setId(symptom.getId());
            symptomResponse.setName(symptom.getName());
            symptomResponse.setStatus(symptom.isStatus());
            responses.add(symptomResponse);
        }
        return responses;
    }

    @Override
    public List<SymptomResponse> findSymptomByIdIn(List<Long> ids) {
        List<SymptomResponse> responses = new ArrayList<>();
        List<Symptom> symptomList = symptomRepository.findSymptomByIdIn(ids);
        for (Symptom symptom : symptomList) {
            SymptomResponse symptomResponse = modelMapper.map(symptom, SymptomResponse.class);
            responses.add(symptomResponse);
        }
        return responses;
    }
}
