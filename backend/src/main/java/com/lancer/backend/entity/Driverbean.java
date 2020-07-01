package com.lancer.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "driver_info")
public class Driverbean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer driverId;

    @Column(length = 20)
    private String name;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Driverbean(){

    }

    public Driverbean(Integer driverId, String name) {
        this.driverId = driverId;
        this.name = name;
    }
}