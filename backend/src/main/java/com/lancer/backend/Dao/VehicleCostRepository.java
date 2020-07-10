package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.VehicleCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface VehicleCostRepository extends JpaRepository<VehicleCost,Long> {
    List<VehicleCost> findByCarIdLike(Long id);
}