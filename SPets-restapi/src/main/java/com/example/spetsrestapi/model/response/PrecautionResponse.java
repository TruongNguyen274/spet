package com.example.spetsrestapi.model.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrecautionResponse implements Serializable {

    private String name;
    private boolean status;

}
