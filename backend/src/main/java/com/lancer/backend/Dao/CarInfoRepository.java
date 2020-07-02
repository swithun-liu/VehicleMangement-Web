package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInfoRepository extends JpaRepository<Car,Long>{
    List<Car> findByCarIdLike(Long id);
}