package com.example.spetsrestapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse extends BaseResponse {

    private long id;

    private String activityDate;

    private String activityType;

    private String title;

    private String description;

    private String startTime;

    private String repeatInterval;

    private boolean status;

    // pet
    private PetResponse pet;

}