package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

public class MediaResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("pet")
    private PetResponse pet;

    @SerializedName("path")
    private String path;

    @SerializedName("status")
    private boolean status;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
