package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.Transportation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation,String> {
    List<Transportation> findByTransportationIdLike(String id);
}