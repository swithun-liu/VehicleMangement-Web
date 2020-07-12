package com.lancer.backend.message;

import java.util.ArrayList;
import java.util.List;

import com.lancer.backend.entity.Car;

/**
 * DistributePlan
 */
public class DistributePlan {
    public List<List<Car>> list=new ArrayList<>();
    public int totalCost;

    public DistributePlan(List<List<Car>> list) {
        this.list = list;
    }

    public DistributePlan() {
    }

    public List<List<Car>> getList() {
        return list;
    }

    public void setList(List<List<Car>> list) {
        this.list = list;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}