package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
public class HealthRecordSaveRequest implements Serializable {

    private Long id;

    @NotNull(message = "Pet is required!")
    private Long petId;

    @NotBlank(message = "Health is required!")
    private String health;

    @NotNull(message = "Height is required!")
    private Double height;

    @NotNull(message = "Weight is required!")
    private Double weight;

}
