package com.example.spetsmobile.model.request;

import com.google.gson.annotations.SerializedName;

public class HealthRecordResquest {

    @SerializedName("id")
    private long id;

    @SerializedName("petId")
    private long petId;

    @SerializedName("health")
    private String health;

    @SerializedName("height")
    private double height;

    @SerializedName("weight")
    private double weight;

    public HealthRecordResquest() {
    }

    public HealthRecordResquest(long id, long petId, String health, double height, double weight) {
        this.id = id;
        this.petId = petId;
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

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
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
}
