package com.lancer.backend.Dao;

import com.lancer.backend.entity.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverInfoRepository extends  JpaRepository<Driver,Long>{
    
}