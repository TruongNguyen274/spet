package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Disease;
import com.example.spetsrestapi.model.entity.DiseasePrecaution;
import com.example.spetsrestapi.repository.DiseasePrecautionRepository;
import com.example.spetsrestapi.service.DiseasePrecautionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseasePrecautionServiceImpl implements DiseasePrecautionService {

    @Autowired
    private DiseasePrecautionRepository diseasePrecautionRepository;

    @Override
    public List<DiseasePrecaution> findByDisease(Disease disease) {
        return diseasePrecautionRepository.findByDiseaseAndStatusIsTrue(disease);
    }

}
