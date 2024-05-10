package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiseaseResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("status")
    private boolean status;

    @SerializedName("symptomResponseList")
    private List<SymptomResponse> symptomResponseList;

    @SerializedName("precautionResponseList")
    private List<PrecautionResponse> precautionResponseList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<SymptomResponse> getSymptomResponseList() {
        return symptomResponseList;
    }

    public void setSymptomResponseList(List<SymptomResponse> symptomResponseList) {
        this.symptomResponseList = symptomResponseList;
    }

    public List<PrecautionResponse> getPrecautionResponseList() {
        return precautionResponseList;
    }

    public void setPrecautionResponseList(List<PrecautionResponse> precautionResponseList) {
        this.precautionResponseList = precautionResponseList;
    }
}
