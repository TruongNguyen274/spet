package com.example.spetsrestapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class DiseasePrecautionId implements Serializable {

    Long diseaseId;

    Long precautionId;

}

