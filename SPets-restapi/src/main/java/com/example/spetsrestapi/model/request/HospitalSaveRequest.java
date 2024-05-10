package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class HospitalSaveRequest implements Serializable {

    private Long id;

    @NotNull(message = "Account is required!")
    @Min(value = 1, message = "Account is required!")
    private Long accountId;

    private String name;

    private String address;

    private String phone;

    private String email;

    private boolean status;

}
