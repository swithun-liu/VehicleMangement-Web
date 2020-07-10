package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.VehicleCostRepository;
import com.lancer.backend.entity.VehicleCost;
import com.lancer.backend.service.VehicleCostServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleCostServImpl implements VehicleCostServ {
    
    @Autowired
    private VehicleCostRepository vehicleCostRepository;

    @Override
    public List<VehicleCost> findAll(){
        return vehicleCostRepository.findAll();
    }

    @Override
    public VehicleCost AddOne(VehicleCost entity){
        return vehicleCostRepository.save(entity);
    }

    @Override
    public VehicleCost getOnebyId(Long id){
        return vehicleCostRepository.getOne(id);
    }

    @Override
    public VehicleCost update(VehicleCost entity){
        return vehicleCostRepository.save(entity);
    }

    @Override
    public boolean delete(Long id){
        try{
            vehicleCostRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<VehicleCost> findByCarIdLike(Long id){
        return vehicleCostRepository.findByCarIdLike(id);
    }
}