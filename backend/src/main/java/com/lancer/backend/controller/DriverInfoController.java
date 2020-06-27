package com.lancer.backend.controller;

import java.util.List;

import com.lancer.backend.Dao.DriverInfoRepository;
import com.lancer.backend.entity.Driverbean;
import com.lancer.backend.service.Impl.DriverServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverInfoController {

    @Autowired
    private DriverServImpl driverServ=new DriverServImpl();

    @GetMapping("/driverinfo")
    public List<Driverbean> findAll(){
        return driverServ.findAll();
    }
}