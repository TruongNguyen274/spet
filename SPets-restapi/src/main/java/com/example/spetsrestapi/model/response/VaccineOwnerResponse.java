package com.example.spetsrestapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccineOwnerResponse extends BaseResponse {

    // media
    private long id;
    private String name;
    private String date;
    private String status;

    // pet
    private PetResponse pet;
}