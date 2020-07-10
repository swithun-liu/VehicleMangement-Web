package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Registration;

public interface RegistrationServ {
    public List<Registration> findAll();
    public Registration AddOne(Registration entity);
    public Registration getOnebyId(String id);
    public Registration update(Registration entity);
    public boolean delete(String id);
    public List<Registration> findByDispatchIdLike(String id);
}