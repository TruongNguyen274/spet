package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Disease;
import com.example.spetsrestapi.model.entity.DiseaseSymptom;
import com.example.spetsrestapi.model.entity.Symptom;
import com.example.spetsrestapi.model.request.SearchRequest;
import com.example.spetsrestapi.model.response.DiseaseResponse;
import com.example.spetsrestapi.model.response.SymptomResponse;
import com.example.spetsrestapi.service.DiseaseSymptomService;
import com.example.spetsrestapi.service.SearchService;
import com.example.spetsrestapi.service.SymptomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private DiseaseSymptomService diseaseSymptomService;

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DiseaseResponse> findDiseaseBySymptom(SearchRequest searchRequest) {
        if (searchRequest.getSymptomList() == null || searchRequest.getSymptomList().isEmpty()) {
            return null;
        }

        List<Long> symptomIds = searchRequest.getSymptomList().stream()
                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        List<SymptomResponse> symptomResponseList = symptomService.findSymptomByIdIn(symptomIds);
        List<Symptom> symptomList = new ArrayList<>();
        for (SymptomResponse response : symptomResponseList) {
            Symptom symptom = modelMapper.map(response, Symptom.class);
            symptomList.add(symptom);
        }

        List<DiseaseSymptom> diseaseSymptomList = diseaseSymptomService.findBySymptomIn(symptomList);

        Collection<DiseaseResponse> values = getDiseaseResponses(diseaseSymptomList);

        return new ArrayList<>(values);

    }

    private Collection<DiseaseResponse> getDiseaseResponses(List<DiseaseSymptom> diseaseSymptomList) {
        HashMap<Long, DiseaseResponse> diseaseHashMap = new HashMap<>();

        if (diseaseSymptomList != null) {
            for (DiseaseSymptom diseaseSymptom : diseaseSymptomList) {
                Disease disease = diseaseSymptom.getDisease();
                DiseaseResponse diseaseResponse = new DiseaseResponse();
                diseaseResponse.setId(disease.getId());
                diseaseResponse.setName(disease.getName());
                diseaseResponse.setDescription(disease.getDescription());
                diseaseResponse.setStatus(disease.isStatus());
                diseaseHashMap.put(disease.getId(), diseaseResponse);
            }
        }

        return diseaseHashMap.values();
    }
}
