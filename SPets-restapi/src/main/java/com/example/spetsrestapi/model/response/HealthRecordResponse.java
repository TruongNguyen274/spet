package com.example.spetsrestapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordResponse extends BaseResponse{

    // media
    private long id;
    private String health;
    private double height;
    private double weight;

    // pet
    private PetResponse pet;

}