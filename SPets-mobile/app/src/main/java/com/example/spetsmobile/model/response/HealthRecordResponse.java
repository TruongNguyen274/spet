package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

public class HealthRecordResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("health")
    private String health;

    @SerializedName("height")
    private double height;

    @SerializedName("weight")
    private double weight;

    @SerializedName("createdAt")
    private String createdAt;

    public HealthRecordResponse() {
    }

    public HealthRecordResponse(long id, String health, double height, double weight) {
        this.id = id;
        this.health = health;
        this.height = height;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
