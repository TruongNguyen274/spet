package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.entity.*;
import com.example.spetsrestapi.model.request.SearchRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.DiseaseResponse;
import com.example.spetsrestapi.model.response.PrecautionResponse;
import com.example.spetsrestapi.model.response.SymptomResponse;
import com.example.spetsrestapi.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private SymptomService symptomService;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private DiseaseSymptomService diseaseSymptomService;

    @Autowired
    private DiseasePrecautionService diseasePrecautionService;

    @Autowired
    private PrecautionService precautionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/symptom")
    public ResponseEntity<ApiResponse<List<SymptomResponse>>> getAllSymptom(){
        ApiResponse apiResponse = new ApiResponse();
        List<SymptomResponse> symptomResponseList = symptomService.findByActive();
        apiResponse.ok(symptomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/disease")
    public ResponseEntity<ApiResponse<List<DiseaseResponse>>> search(@Valid @RequestBody SearchRequest request){
        ApiResponse apiResponse = new ApiResponse();
        List<DiseaseResponse> diseaseList = searchService.findDiseaseBySymptom(request);
        setValue(diseaseList);
        apiResponse.ok(diseaseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    private void setValue(List<DiseaseResponse> diseaseList) {
        // Lấy thông tin bệnh
        for (DiseaseResponse response : diseaseList) {
            Disease disease = diseaseService.findById(response.getId());

            if (disease == null) {
                continue;
            }

            // lấy danh sách triệu chứng
            List<DiseaseSymptom> diseaseSymptomList = diseaseSymptomService.findByDisease(disease);
            List<Long> symptomIds = diseaseSymptomList.stream()
                    .map((DiseaseSymptom d) -> d.getSymptom().getId())
                    .collect(Collectors.toList());
            List<SymptomResponse> symptomResponseList = symptomService.findSymptomByIdIn(symptomIds);

            // lấy danh sách cách chữa trị
            List<DiseasePrecaution> diseasePrecautionList = diseasePrecautionService.findByDisease(disease);
            List<Long> precautionIds = diseasePrecautionList.stream()
                    .map((DiseasePrecaution d) -> d.getPrecaution().getId())
                    .collect(Collectors.toList());
            List<PrecautionResponse> precautionResponseList = precautionService.findPrecautionByIdIn(precautionIds);

            response.setSymptomResponseList(symptomResponseList);
            response.setPrecautionResponseList(precautionResponseList);
        }
    }

}
