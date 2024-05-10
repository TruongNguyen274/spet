package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.entity.Hospital;
import com.example.spetsrestapi.model.request.HospitalSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.HospitalResponse;
import com.example.spetsrestapi.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService service;

    @GetMapping(value = "/list")
    public ResponseEntity<ApiResponse<List<HospitalResponse>>> getHospitalList() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(service.getHospital());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/account")
    public ResponseEntity<ApiResponse<HospitalResponse>> getHospitalByAccount(@RequestParam long accountId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(service.getHospitalByAccountId(accountId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse<HospitalResponse>> save(@Valid @RequestBody HospitalSaveRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        Hospital hospital = service.findById(request.getId());
        if (hospital == null) {
            hospital = new Hospital();
        }
        hospital.setAccountId(request.getAccountId());
        hospital.setName(request.getName());
        hospital.setEmail(request.getEmail());
        hospital.setPhone(request.getPhone());
        hospital.setAddress(request.getAddress());
        hospital.setStatus(request.isStatus());

        hospital = service.save(hospital);

        apiResponse.ok(hospital);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}