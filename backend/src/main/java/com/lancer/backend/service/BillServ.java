package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Bill;

public interface BillServ {
    public List<Bill> findAll();
    public Bill AddOne(Bill entity);
    public Bill getOnebyId(String id);
    public Bill update(Bill entity);
    public boolean delete(String id);
    public List<Bill> findByBillIdLike(String id);
}