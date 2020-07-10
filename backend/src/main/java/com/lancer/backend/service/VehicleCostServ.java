package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.VehicleCost;

public interface VehicleCostServ {
    public List<VehicleCost> findAll();
    public VehicleCost AddOne(VehicleCost entity);
    public VehicleCost getOnebyId(Long id);
    public VehicleCost update(VehicleCost entity);
    public boolean delete(Long id);
    public List<VehicleCost> findByCarIdLike(Long id);
}