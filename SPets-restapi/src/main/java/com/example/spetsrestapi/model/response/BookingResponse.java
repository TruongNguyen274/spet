package com.example.spetsrestapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse extends BaseResponse {

    private long id;

    private String title;

    private String description;

    private String date;

    private String startTime;

    private String endTime;

    private String status;

    // account
    private AccountResponse account;

    // hospital
    private HospitalResponse hospital;

}