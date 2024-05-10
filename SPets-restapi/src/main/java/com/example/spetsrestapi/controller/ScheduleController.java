package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.request.ScheduleSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.ScheduleResponse;
import com.example.spetsrestapi.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ScheduleResponse>>> findAll() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(scheduleService.getAll());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<ApiResponse<ScheduleResponse>> getById(@RequestParam long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(scheduleService.getById(id));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/pet")
    public ResponseEntity<ApiResponse<ScheduleResponse>> getByPet(@RequestParam long petId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(scheduleService.getByPet(petId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity save(@Valid @RequestBody ScheduleSaveRequest request){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(scheduleService.save(request));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/account")
    public ResponseEntity<ApiResponse<ScheduleResponse>> getByAccount(@RequestParam long accountId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(scheduleService.getByAccount(accountId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam long scheduleId){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(scheduleService.delete(scheduleId));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}