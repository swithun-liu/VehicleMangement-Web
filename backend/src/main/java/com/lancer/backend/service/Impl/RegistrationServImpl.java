package com.lancer.backend.service.Impl;

import java.util.List;

import com.lancer.backend.Dao.RegistrationRepository;
import com.lancer.backend.entity.Registration;
import com.lancer.backend.service.RegistrationServ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServImpl implements RegistrationServ{
    
    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public List<Registration> findAll(){
        return registrationRepository.findAll();
    }

    @Override
    public Registration AddOne(Registration entity){
        return registrationRepository.save(entity);
    }

    @Override
    public Registration getOnebyId(String id) {
        return registrationRepository.getOne(id);
    }

    @Override
    public Registration update(Registration entity) {
        return registrationRepository.save(entity);
    }

    @Override
    public boolean delete(String id) {
        try{
            registrationRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Registration> findByDispatchIdLike(String id) {
        return registrationRepository.findByDispatchIdLike(id);
    }
}