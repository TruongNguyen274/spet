package com.example.spetsrestapi.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseResponse implements Serializable {

    private long id;
    private String name;
    private String description;
    private boolean status;

    private List<PrecautionResponse> precautionResponseList;
    private List<SymptomResponse> symptomResponseList;

}
