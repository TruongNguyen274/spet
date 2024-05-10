package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

public class PrecautionResponse {

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private boolean status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
