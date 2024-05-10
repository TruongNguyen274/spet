package com.example.spetsrestapi.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class NotificationRequestDTO {

    private List<String> targetMultiDevice;
    private String target;
    private String title;
    private String body;

}

