package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.BillRepository;
import com.lancer.backend.entity.Bill;
import com.lancer.backend.service.BillServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServImpl implements BillServ{
    
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findAll(){
        return billRepository.findAll();
    }

    @Override
    public Bill AddOne(Bill entity){
        return billRepository.save(entity);
    }

    @Override
    public Bill getOnebyId(String id) {
        return billRepository.getOne(id);
    }

    @Override
    public Bill update(Bill entity) {
        return billRepository.save(entity);
    }

    @Override
    public boolean delete(String id) {
        try{
            billRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Bill> findByBillIdLike(String id) {
        return billRepository.findByBillIdLike(id);
    }
}