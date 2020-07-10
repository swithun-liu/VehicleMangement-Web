package com.lancer.backend.controller;

import java.util.Date;
import java.util.List;

import com.lancer.backend.entity.Maintenance;
import com.lancer.backend.service.Impl.MaintenanceServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MaintenanceInfoController {
    
    @Autowired
    private MaintenanceServImpl maintenanceServImpl;

    /***
     * 根据参数获取维修信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/maintenanceinfo")
    public List<Maintenance> findAll(@RequestParam(name = "query") String info){
        if(info.length()!=0){
            return maintenanceServImpl.findByMaintenanceIdLike(info);
        }else{
            return maintenanceServImpl.findAll();
        }
    }

    /**
     * 添加维修单
     * @param entity
     * @return
     */
    @PostMapping(value="/maintenanceinfo/addmaintenance")
    public String AddOne(@RequestBody Maintenance entity) {
        String maintenanceId = String.valueOf(new Date().getTime());
        entity.setMaintenanceId(maintenanceId);
        if(maintenanceServImpl.AddOne(entity)!=null){
            return "成功";
        }else{
            return "失败";
        }
    }

    /**
     * 根据id获取维修单信息
     * @param param
     * @return
     */
    @GetMapping(value="/maintenanceinfo/edit/{id}")
    public Maintenance getOneforEdit(@PathVariable("id") String param) {
        if(param == null){
            return null;
        }else{
            return maintenanceServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑维修单信息
     * @param entity
     * @return
     */
    @PostMapping(value="/maintenanceinfo/editinfo")
    public String updateMaintenanceInfo(@RequestBody Maintenance entity) {
        if(maintenanceServImpl.update(entity) !=null){
            return "成功";
        }else{
            return "失败";
        }
    }
    
    /**
     * 删除维修单
     * @param param
     * @return
     */
    @DeleteMapping(value="/maintenanceinfo/delete/{id}")
    public String deleteMaintenance(@PathVariable("id") String param){
        if(maintenanceServImpl.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }

}