package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.Dao.DriverInfoRepository;
import com.lancer.backend.entity.Driverbean;

public interface DriverServ {
    public List<Driverbean> findAll();
    public boolean AddOne();
}