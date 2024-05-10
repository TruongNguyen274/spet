package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.entity.Disease;
import com.example.spetsrestapi.model.entity.DiseaseSymptom;
import com.example.spetsrestapi.model.entity.Symptom;

import java.util.List;

public interface DiseaseSymptomService {

    List<DiseaseSymptom> findByDisease(Disease disease);

    List<DiseaseSymptom> findBySymptomIn(List<Symptom> symptomList);

}
