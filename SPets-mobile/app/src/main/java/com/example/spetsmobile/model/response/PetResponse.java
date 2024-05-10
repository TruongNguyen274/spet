package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PetResponse {

    // pet
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("species")
    private String species;

    @SerializedName("birthdate")
    private String birthdate;

    @SerializedName("color")
    private String color;

    @SerializedName("weight")
    private double weight;

    @SerializedName("height")
    private double height;

    @SerializedName("health")
    private String health;

    @SerializedName("status")
    private boolean status;

    @SerializedName("vaccineList")
    private List<VaccineResponse> vaccineList;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<VaccineResponse> getVaccineList() {
        return vaccineList;
    }

    public void setVaccineList(List<VaccineResponse> vaccineList) {
        this.vaccineList = vaccineList;
    }
}
