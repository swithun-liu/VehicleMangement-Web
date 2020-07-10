package com.lancer.backend.controller;

import java.util.Date;
import java.util.List;

import com.lancer.backend.entity.Transportation;
import com.lancer.backend.service.Impl.TransportationServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class TransportationInfoController {

    @Autowired
    private TransportationServImpl transportationServImpl;

    /***
     * 根据参数获取维修信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/transportationinfo")
    public List<Transportation> findAll(@RequestParam(name = "query") String info){
        if(info.length()!=0){
            return transportationServImpl.findByTransportationIdLike(info);
        }else{
            return transportationServImpl.findAll();
        }
    }

    /**
     * 添加维修单
     * @param entity
     * @return
     */
    @PostMapping(value="/transportationinfo/addtransportationinfo")
    public String AddOne(@RequestBody Transportation entity) {
        String transportationId = String.valueOf(new Date().getTime());
        entity.setTransportationId(transportationId);
        if(transportationServImpl.AddOne(entity)!=null){
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
    @GetMapping(value="/transportationinfo/edit/{id}")
    public Transportation getOneforEdit(@PathVariable("id") String param) {
        if(param == null){
            return null;
        }else{
            return transportationServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑维修单信息
     * @param entity
     * @return
     */
    @PostMapping(value="/transportationinfo/editinfo")
    public String updateTransportationInfo(@RequestBody Transportation entity) {
        if(transportationServImpl.update(entity) !=null){
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
    @DeleteMapping(value="/transportationinfo/delete/{id}")
    public String deleteTransportation(@PathVariable("id") String param){
        if(transportationServImpl.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }
}