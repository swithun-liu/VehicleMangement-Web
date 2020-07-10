package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.TransportationRepository;
import com.lancer.backend.entity.Transportation;
import com.lancer.backend.service.TransportationServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportationServImpl implements TransportationServ {
    
    @Autowired
    private TransportationRepository transportationRepository;

    @Override
    public List<Transportation> findAll(){
        return transportationRepository.findAll();
    }

    @Override
    public Transportation AddOne(Transportation entity){
        return transportationRepository.save(entity);
    }

    @Override
    public Transportation getOnebyId(String id) {
        return transportationRepository.getOne(id);
    }

    @Override
    public Transportation update(Transportation entity) {
        return transportationRepository.save(entity);
    }

    @Override
    public boolean delete(String id) {
        try{
            transportationRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Transportation> findByTransportationIdLike(String id) {
        return transportationRepository.findByTransportationIdLike(id);
    }
}