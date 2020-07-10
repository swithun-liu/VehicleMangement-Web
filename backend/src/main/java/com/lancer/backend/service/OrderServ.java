package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Order;

public interface OrderServ {
    public List<Order> findAll();
    public Order AddOne(Order entity);
    public Order getOnebyId(String id);
    public Order update(Order entity);
    public boolean delete(String id);
    public List<Order> findByOrderIdLike(String id);
}