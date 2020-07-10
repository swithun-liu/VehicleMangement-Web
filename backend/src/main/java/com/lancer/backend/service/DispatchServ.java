package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Dispatch;

public interface DispatchServ {
    public List<Dispatch> findAll();
    public Dispatch AddOne(Dispatch entity);
    public Dispatch getOnebyId(String id);
    public Dispatch update(Dispatch entity);
    public boolean delete(String id);
    public List<Dispatch> findByDispatchIdLike(String id);
}