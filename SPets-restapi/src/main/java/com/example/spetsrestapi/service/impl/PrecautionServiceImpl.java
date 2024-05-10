package com.example.spetsrestapi.service.impl;

import com.example.spetsrestapi.model.entity.Precaution;
import com.example.spetsrestapi.model.response.PrecautionResponse;
import com.example.spetsrestapi.repository.PrecautionRepository;
import com.example.spetsrestapi.service.PrecautionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrecautionServiceImpl implements PrecautionService {

    @Autowired
    private PrecautionRepository precautionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PrecautionResponse> findPrecautionByIdIn(List<Long> ids) {
        List<PrecautionResponse> responseList = new ArrayList<>();
        List<Precaution> precautionList = precautionRepository.findPrecautionByIdIn(ids);

        for (Precaution precaution: precautionList) {
            PrecautionResponse response = new PrecautionResponse();
            response.setName(precaution.getName());
            response.setStatus(precaution.isStatus());
            responseList.add(response);
        }

        return responseList;
    }
}
