package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Car;

public interface CarServ {
    public List<Car> findAll();
    public Car AddOne(Car entity);
    public Car getOnebyId(Long id);
    public Car update(Car entity);
    public boolean delete(Long id);
    public List<Car> findByCarIdLike(Long name);
}