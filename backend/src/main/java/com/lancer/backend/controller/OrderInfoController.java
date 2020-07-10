package com.lancer.backend.controller;

import java.util.Date;
import java.util.List;

import com.lancer.backend.entity.Order;
import com.lancer.backend.service.Impl.OrderServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderInfoController {
    
    @Autowired
    private OrderServImpl orderServImpl;

    /***
     * 根据参数获取订单信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/orderinfo")
    private List<Order> findAll(@RequestParam(name = "query") String info){
        if(info.length()!=0){
            return orderServImpl.findByOrderIdLike(info);
        }else{
            return orderServImpl.findAll();
        }
    }

    /**
     * 添加订单
     * @param entity
     * @return
     */
    @PostMapping(value = "/orderinfo/addorderinfo")
    private String AddOne(@RequestBody Order entity){
        String orderId = String.valueOf(new Date().getTime());
        entity.setOrderId(orderId);
        if(orderServImpl.AddOne(entity)!=null){
            return "成功";
        }else {
            return "失败";
        }
    }

    /**
     * 根据id获取订单信息
     * @param param
     * @return
     */
    @GetMapping(value = "/orderinfo/edit/{id}")
    private Order getOneforEdit(@PathVariable("id") String param){
        if(param == null){
            return null;
        }else{
            return orderServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑订单信息
     * @param entity
     * @return
     */
    @PostMapping(value="/orderinfo/editinfo")
    public String updateOrderInfo(@RequestBody Order entity) {
        if(orderServImpl.update(entity) !=null){
            return "成功";
        }else{
            return "失败";
        }
    }
    
    /**
     * 删除订单
     * @param param
     * @return
     */
    @DeleteMapping(value="/orderinfo/delete/{id}")
    public String deleteOrder(@PathVariable("id") String param){
        if(orderServImpl.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }
}