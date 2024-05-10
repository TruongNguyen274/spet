package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

public class VaccineResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("pet")
    private PetResponse pet;

    @SerializedName("name")
    private String name;

    @SerializedName("date")
    private String date;

    @SerializedName("status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PetResponse getPet() {
        return pet;
    }

    public void setPet(PetResponse pet) {
        this.pet = pet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
