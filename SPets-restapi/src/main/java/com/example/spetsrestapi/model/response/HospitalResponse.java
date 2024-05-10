package com.example.spetsrestapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalResponse extends BaseResponse {

    private long id;

    private String name;

    private String address;

    private String phone;

    private String email;

    private boolean status;

    // account
    private long accountId;

}