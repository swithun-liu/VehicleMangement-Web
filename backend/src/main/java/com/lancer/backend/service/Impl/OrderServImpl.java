package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.OrderRepository;
import com.lancer.backend.entity.Order;
import com.lancer.backend.service.OrderServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServImpl implements OrderServ{
    
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    public Order AddOne(Order entity){
        return orderRepository.save(entity);
    }

    @Override
    public Order getOnebyId(String id){
        return orderRepository.getOne(id);
    }

    @Override
    public Order update(Order entity){
        return orderRepository.save(entity);
    }

    @Override
    public boolean delete(String id){
        try{
            orderRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public List<Order> findByOrderIdLike(String id){
        return orderRepository.findByOrderIdLike(id);
    }
}