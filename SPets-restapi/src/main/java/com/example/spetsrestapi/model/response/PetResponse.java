package com.example.spetsrestapi.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse extends BaseResponse {

    // pet
    private long id;
    private String name;
    private String avatar;
    private String species;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date birthdate;
    private String color;
    private double weight;
    private double height;
    private String health;
    private boolean status;

    // owner
    private AccountResponse owner;

}