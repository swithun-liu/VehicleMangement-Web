package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Driver;


public interface DriverServ {
    public List<Driver> findAll();
    public Driver AddOne(Driver entity);
    public Driver getOnebyId(Long id);
    public Driver update(Driver entity);
    public boolean delete(Long id);
    public List<Driver> findByDriverNameLike(String name);
}