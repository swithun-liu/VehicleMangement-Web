package com.lancer.backend.controller;

import java.util.Date;

import com.lancer.backend.entity.Driver;
import com.lancer.backend.service.Impl.DriverServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DriverInfoController {

    @Autowired
    private DriverServImpl driverServ = new DriverServImpl();

    /**
     * 根据参数获取司机信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/driverinfo")
    public Page<Driver> findAll(@RequestParam(name = "query") String info, @RequestParam(name = "pagenum") int pagenum,
            @RequestParam(name = "pagesize") int pagesize) {
        PageRequest pageRequest = PageRequest.of(pagenum-1, pagesize);
        if (info.length() > 0) {
            // return driverServ.findByDriverNameLike("%"+info+"%");
            Driver driver = new Driver();
            driver.setDriverId(115);
            driver.setDriverName(info);
            ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("driverName",GenericPropertyMatchers.contains()).withIgnorePaths("driverId");
            Example<Driver> example = Example.of(driver, exampleMatcher);
            return driverServ.findAllbyPage(example, pageRequest);
        } else {
            // return driverServ.findAll();
            return driverServ.findAll(pageRequest);
        }
    }

    /**
     * 添加司机
     * 
     * @param entity
     * @return
     */
    @PostMapping(value = "/driverinfo/adduser")
    public String addOne(@RequestBody Driver entity) {
        entity.setDriverId(2);
        entity.setEntryDate(new java.sql.Date(new Date().getTime()));
        if (driverServ.AddOne(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 根据id获取司机信息
     * 
     * @param param
     * @return
     */
    @GetMapping(value = "/driverinfo/edit/{id}")
    public Driver getOneforEdit(@PathVariable("id") Long param) {
        if (param == null) {
            return null;
        } else {
            return driverServ.getOnebyId(param);
        }
    }

    /**
     * 编辑司机信息
     * 
     * @param entity
     * @return
     */
    @PostMapping(value = "/driverinfo/editinfo")
    public String updateDriverInfo(@RequestBody Driver entity) {
        if (driverServ.update(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 删除司机
     * 
     * @param param
     * @return
     */
    @DeleteMapping(value = "/driverinfo/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long param) {
        if (driverServ.delete(param)) {
            return "成功";
        } else {
            return "失败";
        }
    }

}