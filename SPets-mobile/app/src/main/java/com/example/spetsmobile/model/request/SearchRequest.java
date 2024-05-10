package com.example.spetsmobile.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchRequest {

    @SerializedName("symptomList")
    private List<String> symptomList;

    @SerializedName("diseaseList")
    private String diseaseList;

    @SerializedName("diseaseIds")
    private String diseaseIds;

    public List<String> getSymptomList() {
        return symptomList;
    }

    public void setSymptomList(List<String> symptomList) {
        this.symptomList = symptomList;
    }

    public String getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(String diseaseList) {
        this.diseaseList = diseaseList;
    }

    public String getDiseaseIds() {
        return diseaseIds;
    }

    public void setDiseaseIds(String diseaseIds) {
        this.diseaseIds = diseaseIds;
    }
}
