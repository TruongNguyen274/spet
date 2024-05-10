package com.example.spetsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "activity_date")
    private String activityDate;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "repeat_interval")
    private String repeatInterval;

    @Column(name = "status")
    private boolean status;

}
