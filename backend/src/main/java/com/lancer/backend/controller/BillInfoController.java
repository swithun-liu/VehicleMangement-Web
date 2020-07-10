package com.lancer.backend.controller;

import java.util.Date;
import java.util.List;

import com.lancer.backend.entity.Bill;
import com.lancer.backend.service.Impl.BillServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BillInfoController {

    @Autowired
    private BillServImpl billServImpl;

    /***
     * 根据参数获取维修信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/billinfo")
    public List<Bill> findAll(@RequestParam(name = "query") String info){
        if(info.length()!=0){
            return billServImpl.findByBillIdLike(info);
        }else{
            return billServImpl.findAll();
        }
    }

    /**
     * 添加维修单
     * @param entity
     * @return
     */
    @PostMapping(value="/billinfo/addbillinfo")
    public String AddOne(@RequestBody Bill entity) {
        String billId = String.valueOf(new Date().getTime());
        entity.setBillId(billId);
        if(billServImpl.AddOne(entity)!=null){
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
    @GetMapping(value="/billinfo/edit/{id}")
    public Bill getOneforEdit(@PathVariable("id") String param) {
        if(param == null){
            return null;
        }else{
            return billServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑维修单信息
     * @param entity
     * @return
     */
    @PostMapping(value="/billinfo/editinfo")
    public String updateBillInfo(@RequestBody Bill entity) {
        if(billServImpl.update(entity) !=null){
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
    @DeleteMapping(value="/billinfo/delete/{id}")
    public String deleteBill(@PathVariable("id") String param){
        if(billServImpl.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }
}