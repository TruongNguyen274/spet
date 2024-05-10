package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class VaccineRequest implements Serializable {

    @NotNull(message = "PetId is required!")
    private long petId;

    @NotBlank(message = "Date is required!")
    private String date;

    @NotBlank(message = "Name is required!")
    private String name;

}
