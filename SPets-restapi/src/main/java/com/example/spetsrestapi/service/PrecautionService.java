package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.response.PrecautionResponse;

import java.util.List;

public interface PrecautionService {

    List<PrecautionResponse> findPrecautionByIdIn(List<Long> ids);

}
