package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.MaintenanceInfoRepository;
import com.lancer.backend.entity.Maintenance;
import com.lancer.backend.service.MaintenanceServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServImpl implements MaintenanceServ{
    
    @Autowired
    private MaintenanceInfoRepository maintenanceInfoRepository;

    /** 返回所有车辆维修信息 */
    @Override
    public List<Maintenance> findAll(){
        return maintenanceInfoRepository.findAll();
    }

    @Override
    public Maintenance AddOne(Maintenance entity){
        return maintenanceInfoRepository.save(entity);
    }

    @Override
    public Maintenance getOnebyId(String id) {
        return maintenanceInfoRepository.getOne(id);
    }

    @Override
    public Maintenance update(Maintenance entity) {
        return maintenanceInfoRepository.save(entity);
    }

    @Override
    public boolean delete(String id) {
        try{
            maintenanceInfoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Maintenance> findByMaintenanceIdLike(String name) {
        return maintenanceInfoRepository.findByMaintenanceIdLike(name);
    }
}