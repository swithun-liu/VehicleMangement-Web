package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Car;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CarServ {
    public List<Car> findAll();
    public Car AddOne(Car entity);
    public Car getOnebyId(Long id);
    public Car update(Car entity);
    public boolean delete(Long id);
    public List<Car> findByCarIdLike(Long name);
    public Page<Car> findAllbyPage(PageRequest pageRequest);
}