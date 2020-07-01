package com.lancer.backend.Dao;

import com.lancer.backend.entity.Entitytest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitytestRespository extends JpaRepository<Entitytest,Long>{
    
}