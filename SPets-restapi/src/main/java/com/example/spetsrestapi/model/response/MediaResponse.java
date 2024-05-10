package com.example.spetsrestapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaResponse extends BaseResponse{

    // media
    private long id;
    private String title;
    private String path;
    private String description;
    private String recordType;
    private boolean status;

    // pet
    private PetResponse pet;

}