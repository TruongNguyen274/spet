package com.example.spetsrestapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class DiseaseSymptomId implements Serializable {

    Long diseaseId;

    Long symptomId;

}
