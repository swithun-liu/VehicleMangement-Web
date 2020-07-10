package com.lancer.backend.service;

import java.util.List;

import com.lancer.backend.entity.Maintenance;

public interface MaintenanceServ {
    public List<Maintenance> findAll();
    public Maintenance AddOne(Maintenance entity);
    public Maintenance getOnebyId(String id);
    public Maintenance update(Maintenance entity);
    public boolean delete(String id);
    public List<Maintenance> findByMaintenanceIdLike(String name);
}