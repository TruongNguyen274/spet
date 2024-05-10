package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.entity.Disease;
import com.example.spetsrestapi.model.entity.DiseasePrecaution;

import java.util.List;

public interface DiseasePrecautionService {

    List<DiseasePrecaution> findByDisease(Disease disease);

}
