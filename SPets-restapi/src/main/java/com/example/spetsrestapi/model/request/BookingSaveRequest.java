package com.example.spetsrestapi.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BookingSaveRequest implements Serializable {

    private long id;

    private long accountId;

    private long hospitalId;

    private String title;

    private String description;

    private String date;

    private String startTime;

    private String endTime;

    private String status;

}
