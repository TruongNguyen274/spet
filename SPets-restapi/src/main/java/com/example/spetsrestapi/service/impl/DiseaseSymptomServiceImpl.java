package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Disease;
import com.example.spetsrestapi.model.entity.DiseaseSymptom;
import com.example.spetsrestapi.model.entity.Symptom;
import com.example.spetsrestapi.repository.DiseaseSymptomRepository;
import com.example.spetsrestapi.service.DiseaseSymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseSymptomServiceImpl implements DiseaseSymptomService {

    @Autowired
    private DiseaseSymptomRepository diseaseSymptomRepository;

    @Override
    public List<DiseaseSymptom> findByDisease(Disease disease) {
        return diseaseSymptomRepository.findByDiseaseAndStatusIsTrue(disease);
    }

    @Override
    public List<DiseaseSymptom> findBySymptomIn(List<Symptom> symptomList) {
        return diseaseSymptomRepository.findBySymptomIn(symptomList);
    }

}
