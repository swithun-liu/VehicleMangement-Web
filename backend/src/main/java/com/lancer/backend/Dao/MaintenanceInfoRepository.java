package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.Maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceInfoRepository  extends JpaRepository<Maintenance,String> {
    List<Maintenance> findByMaintenanceIdLike(String name);
}