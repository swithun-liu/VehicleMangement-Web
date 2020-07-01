package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.DriverInfoRepository;
import com.lancer.backend.entity.Driver;
import com.lancer.backend.service.DriverServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServImpl implements DriverServ {

    @Autowired
    private DriverInfoRepository driverInfoRepository;

    /** 返回所有司机信息 */
    @Override
    public List<Driver> findAll() {
        return driverInfoRepository.findAll();
    }

    @Override
    public Driver AddOne(Driver entity) {
        return driverInfoRepository.save(entity);
    }

    @Override
    public Driver getOnebyId(Long id) {
        return driverInfoRepository.getOne(id);
    }

    @Override
    public Driver update(Driver entity) {
        return driverInfoRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            driverInfoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public List<Driver> findByDriverNameLike(String name){
        return driverInfoRepository.findByDriverNameLike(name);
    }

}