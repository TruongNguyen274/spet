package com.example.spetsmobile.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VaccineRequest implements Serializable {

    @SerializedName("petId")
    private long petId;

    @SerializedName("date")
    private String date;

    @SerializedName("name")
    private String name;

    public VaccineRequest() {
    }

    public VaccineRequest(long petId, String date, String name) {
        this.petId = petId;
        this.date = date;
        this.name = name;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
