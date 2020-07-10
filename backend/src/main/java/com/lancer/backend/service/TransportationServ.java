package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Transportation;

public interface TransportationServ {
    List<Transportation> findAll();
    public Transportation AddOne(Transportation entity);
    public Transportation getOnebyId(String id);
    public Transportation update(Transportation entity);
    public boolean delete(String id);
    public List<Transportation> findByTransportationIdLike(String id);
}