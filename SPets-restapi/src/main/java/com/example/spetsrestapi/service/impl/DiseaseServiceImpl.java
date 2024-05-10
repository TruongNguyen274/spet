package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Disease;
import com.example.spetsrestapi.repository.DiseaseRepository;
import com.example.spetsrestapi.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;


    @Override
    public Disease findById(long id) {
        return diseaseRepository.findById(id).orElse(null);
    }
}
