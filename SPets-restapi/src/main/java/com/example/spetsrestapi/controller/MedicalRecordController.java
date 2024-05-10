package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.request.MedicalRecordSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.MedicalRecordResponse;
import com.example.spetsrestapi.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicals/record")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(value = "/pet")
    public ResponseEntity<ApiResponse<List<MedicalRecordResponse>>> getAllByPet(@RequestParam long petId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(medicalRecordService.getAllByPetOrderByUpdatedAtDesc(petId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping(value = "/hospital")
    public ResponseEntity<ApiResponse<List<MedicalRecordResponse>>> getAllByHospital(@RequestParam long hospitalId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(medicalRecordService.getAllByHospitalOrderByUpdatedAtDesc(hospitalId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse<MedicalRecordResponse>> medicalRecordSave(@RequestBody MedicalRecordSaveRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(medicalRecordService.save(request));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
