package com.example.spetsrestapi.service;

import com.example.spetsrestapi.model.request.SearchRequest;
import com.example.spetsrestapi.model.response.DiseaseResponse;

import java.util.List;

public interface SearchService {

    List<DiseaseResponse> findDiseaseBySymptom(SearchRequest searchRequest);

}
