package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.Bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,String>{
    List<Bill> findByBillIdLike(String id);
}