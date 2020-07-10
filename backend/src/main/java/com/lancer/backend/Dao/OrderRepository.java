package com.lancer.backend.Dao;

import java.util.List;

import com.lancer.backend.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String>{
    List<Order> findByOrderIdLike(String id);
}