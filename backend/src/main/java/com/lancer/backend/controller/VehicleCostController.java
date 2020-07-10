package com.lancer.backend.controller;

import java.util.List;

import com.lancer.backend.entity.VehicleCost;
import com.lancer.backend.service.Impl.VehicleCostServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleCostController {

    @Autowired
    private VehicleCostServImpl vehicleCostServImpl;

    /***
     * 根据参数获取车辆费用信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/vehiclecostinfo")
    private List<VehicleCost> findAll(@RequestParam(name = "query") Long info){
        if(info!=null){
            return vehicleCostServImpl.findByCarIdLike(info);
        }else{
            return vehicleCostServImpl.findAll();
        }
    }

    /**
     * 添加车辆费用单
     * @param entity
     * @return
     */
    @PostMapping(value = "/vehiclecostinfo/addvehiclecost")
    private String AddOne(@RequestBody VehicleCost entity){
        if(vehicleCostServImpl.AddOne(entity)!=null){
            return "成功";
        }else {
            return "失败";
        }
    }

    /**
     * 根据id获取车辆费用单信息
     * @param param
     * @return
     */
    @GetMapping(value = "/vehiclecostinfo/edit/{id}")
    private VehicleCost getOneforEdit(@PathVariable("id") Long param){
        if(param == null){
            return null;
        }else{
            return vehicleCostServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑车辆费用信息
     * @param entity
     * @return
     */
    @PostMapping(value="/vehiclecostinfo/editinfo")
    public String updateVehicleCostInfo(@RequestBody VehicleCost entity) {
        if(vehicleCostServImpl.update(entity) !=null){
            return "成功";
        }else{
            return "失败";
        }
    }
    
    /**
     * 删除车辆费用单
     * @param param
     * @return
     */
    @DeleteMapping(value="/vehiclecostinfo/delete/{id}")
    public String deleteVehicleCost(@PathVariable("id") Long param){
        if(vehicleCostServImpl.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }
}