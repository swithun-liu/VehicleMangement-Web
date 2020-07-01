package com.lancer.backend.controller;

import java.util.List;

import com.lancer.backend.entity.Driver;
import com.lancer.backend.service.Impl.DriverServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DriverInfoController {

    @Autowired
    private DriverServImpl driverServ = new DriverServImpl();

    @GetMapping("/driverinfo")
    public List<Driver> findAll() {
        return driverServ.findAll();
    }

    @PostMapping(value = "/driverinfo/adduser")
    public String addOne(@RequestBody Driver entity) {
        entity.setDriverId(2);
        if (driverServ.AddOne(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    @GetMapping(value = "/driverinfo/edit/{id}")
    public Driver getOneforEdit(@PathVariable("id") Long param) {
        if (param == null) {
            return null;
        } else{
            return driverServ.getOnebyId(param);
        }
    }

    @PostMapping(value="/driverinfo/editinfo")
    public String updateDriverInfo(@RequestBody Driver entity) {
       if (driverServ.update(entity)!=null){
           return "成功";
       }else{
           return "失败";
       }
    }
    
    @DeleteMapping(value = "/driverinfo/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long param){
        if(driverServ.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }
    

}