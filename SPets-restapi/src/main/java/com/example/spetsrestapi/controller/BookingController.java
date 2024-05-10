package com.example.spetsrestapi.controller;

import com.example.spetsrestapi.model.dto.NotificationRequestDTO;
import com.example.spetsrestapi.model.entity.Account;
import com.example.spetsrestapi.model.entity.Hospital;
import com.example.spetsrestapi.model.request.BookingSaveRequest;
import com.example.spetsrestapi.model.response.ApiResponse;
import com.example.spetsrestapi.model.response.BookingResponse;
import com.example.spetsrestapi.service.AccountService;
import com.example.spetsrestapi.service.BookingService;
import com.example.spetsrestapi.service.HospitalService;
import com.example.spetsrestapi.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/hospital")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getByHospital(@RequestParam long hospitalId,
            @RequestParam(required = false) String status) {
        ApiResponse apiResponse = new ApiResponse();
        if (!status.equalsIgnoreCase("")) {
            apiResponse.ok(bookingService.getAllByStatus(status));
        } else {
            apiResponse.ok(bookingService.getAll());
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse<BookingResponse>> save(@Valid @RequestBody BookingSaveRequest request){
        ApiResponse apiResponse = new ApiResponse();

        BookingResponse bookingResponse = bookingService.save(request);
        apiResponse.ok(bookingResponse);

        if (bookingResponse != null) {
            try {
                Hospital hospital = hospitalService.findById(request.getHospitalId());

                Account account = accountService.findById(hospital.getAccountId());

                String msg = "Vui lòng xác nhận Đồng Ý hoặc Từ Chối cho yêu cầu [" + bookingResponse.getTitle() + " - " + bookingResponse.getDate() +"]";

                NotificationRequestDTO notificationRequestDto = new NotificationRequestDTO();
                notificationRequestDto.setTarget(account.getToken());
                notificationRequestDto.setTitle(String.valueOf(bookingResponse.getId()));
                notificationRequestDto.setBody(msg);
                notificationService.sendPnsToDevice(notificationRequestDto);
            } catch (Exception ex) {

            }
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/status")
    public ResponseEntity<ApiResponse<BookingResponse>> status(@Valid @RequestBody BookingSaveRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        BookingResponse bookingResponse = bookingService.update(request);
        apiResponse.ok(bookingResponse);

        if (bookingResponse != null) {
            try {
                Account account = accountService.findById(bookingResponse.getAccount().getId());
                
                String progress = "đã được chấp nhận";
                if (request.getStatus().equalsIgnoreCase("CANCELLED")) {
                    progress = "đã bị từ chối";
                }

                if (request.getStatus().equalsIgnoreCase("COMPLETED")) {
                    progress = "đã hoàn thành";
                }

                String msg = "Yêu cầu [" + bookingResponse.getTitle() + " - " + bookingResponse.getDate() +"] " + progress;

                NotificationRequestDTO notificationRequestDto = new NotificationRequestDTO();
                notificationRequestDto.setTarget(account.getToken());
                notificationRequestDto.setTitle(String.valueOf(bookingResponse.getId()));
                notificationRequestDto.setBody(msg);
                notificationService.sendPnsToDevice(notificationRequestDto);
            } catch (Exception ex) {

            }
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}