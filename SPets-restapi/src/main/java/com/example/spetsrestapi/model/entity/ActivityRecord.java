package com.example.spetsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "activity_record")
public class ActivityRecord extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "activity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityDate;

    @Column(name = "notes")
    private String notes;

}
