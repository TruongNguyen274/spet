package com.example.spetsrestapi.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class ScheduleSaveRequest implements Serializable {

    private Long id;

    @NotNull(message = "Pet is required!")
    @Min(value = 1, message = "Pet is required!")
    private Long petId;

    private String activityDate;

    private String activityType;

    private String title;

    private String description;

    private String startTime;

    private String repeatInterval;

    private boolean status;

}
