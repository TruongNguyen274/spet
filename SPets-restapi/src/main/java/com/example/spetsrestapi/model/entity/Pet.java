package com.example.spetsrestapi.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "pet")
public class Pet extends BaseEntity implements Serializable {


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Account owner;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "species")
    private String species;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "color")
    private String color;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "health")
    private String health;

    @Column(name = "status")
    private boolean status;


}
