package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.DispatchRepository;
import com.lancer.backend.entity.Dispatch;
import com.lancer.backend.service.DispatchServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispatchServImpl implements DispatchServ{
    
    @Autowired
    private DispatchRepository dispatchRepository;

    @Override
    public List<Dispatch> findAll(){
        return dispatchRepository.findAll();
    }

    @Override
    public Dispatch AddOne(Dispatch entity){
        return dispatchRepository.save(entity);
    }

    @Override
    public Dispatch getOnebyId(String id) {
        return dispatchRepository.getOne(id);
    }

    @Override
    public Dispatch update(Dispatch entity) {
        return dispatchRepository.save(entity);
    }

    @Override
    public boolean delete(String id) {
        try{
            dispatchRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Dispatch> findByDispatchIdLike(String id) {
        return dispatchRepository.findByDispatchIdLike(id);
    }
}