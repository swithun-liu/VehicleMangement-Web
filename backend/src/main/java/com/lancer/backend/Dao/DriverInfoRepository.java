package com.lancer.backend.repository;

import com.lancer.backend.entity.Driverbean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverInfoRepository extends  JpaRepository<Driverbean,Integer>{
    
}