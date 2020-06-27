package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.DriverInfoRepository;
import com.lancer.backend.controller.DriverInfoController;
import com.lancer.backend.entity.Driverbean;
import com.lancer.backend.service.DriverServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServImpl implements DriverServ {

    @Autowired
    private DriverInfoRepository driverInfoRepository;

    /**返回所有司机信息 */
    @Override
    public List<Driverbean> findAll() {
        return driverInfoRepository.findAll();
    }

    @Override
    public boolean AddOne() {
        return false;
    }
    
}