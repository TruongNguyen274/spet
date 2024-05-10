package com.example.spetsrestapi.model.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SymptomResponse {

    private long id;
    private String name;
    private boolean status;

}
