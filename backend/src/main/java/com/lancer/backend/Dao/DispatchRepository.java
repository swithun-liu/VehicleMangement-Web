package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.Dispatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatch,String> {
    List<Dispatch> findByDispatchIdLike(String id);
}