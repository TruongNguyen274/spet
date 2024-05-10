package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.request.HealthRecordSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.HealthRecordResponse;
import com.example.spetsrestapi.service.HealthRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/health/record")
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<HealthRecordResponse>>> findAll() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(healthRecordService.getAll());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/pet")
    public ResponseEntity<ApiResponse<HealthRecordResponse>> getByPet(@RequestParam long petId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(healthRecordService.getByPet(petId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse<HealthRecordResponse>> save(@Valid @RequestBody HealthRecordSaveRequest request){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(healthRecordService.save(request));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}