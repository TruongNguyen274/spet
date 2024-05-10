package com.example.spetsmobile.model.response;

import com.google.gson.annotations.SerializedName;

public class ScheduleResponse {

    @SerializedName("id")
    private long id;

    @SerializedName("pet")
    private PetResponse pet;

    @SerializedName("activityDate")
    private String activityDate;

    @SerializedName("activityType")
    private String activityType;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("startTime")
    private String startTime;

    @SerializedName("repeatInterval")
    private String repeatInterval;

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

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(String repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
