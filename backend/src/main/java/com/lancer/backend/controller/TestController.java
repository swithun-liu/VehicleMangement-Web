package com.lancer.backend.controller;

import java.util.List;

import com.lancer.backend.Dao.DriverInfoRepository;
import com.lancer.backend.entity.Driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class TestController {
    @Autowired
    private DriverInfoRepository driverInfoRepository;

    @GetMapping("/test")
    public List<Driver> findAll() {
        return driverInfoRepository.findAll();
    }
}