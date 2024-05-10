package com.example.spetsrestapi.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class SearchRequest implements Serializable {

    private List<String> symptomList;
    private String diseaseList;
    private String diseaseIds;

}
