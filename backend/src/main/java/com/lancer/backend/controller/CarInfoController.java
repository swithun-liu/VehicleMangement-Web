package com.lancer.backend.controller;

import java.util.Date;

import com.lancer.backend.entity.Car;
import com.lancer.backend.service.Impl.CarServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CarInfoController {

    @Autowired
    private CarServImpl carServ;

    /**
     * 根据参数获取司机信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/carinfo")
    public Page<Car> findAll(@RequestParam(name = "query") Long info) {
        PageRequest pageable = PageRequest.of(0, 4);
        if (info!=null) {
            return carServ.findAllbyPage(pageable);
            // return carServ.findByCarIdLike(info);
        } else {
            return carServ.findAllbyPage(pageable);
        }
    }

    /**
     * 添加司机
     * @param entity
     * @return
     */
    @PostMapping(value = "/carinfo/adduser")
    public String addOne(@RequestBody Car entity) {
        // entity.setCarId((long) 2);
        entity.setPurchaseDate(new java.sql.Date(new Date().getTime()));
        if (carServ.AddOne(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 根据id获取司机信息
     * @param param
     * @return
     */
    @GetMapping(value = "/carinfo/edit/{id}")
    public Car getOneforEdit(@PathVariable("id") Long param) {
        if (param == null) {
            return null;
        } else {
            return carServ.getOnebyId(param);
        }
    }

    /**
     * 编辑司机信息
     * @param entity
     * @return
     */
    @PostMapping(value = "/carinfo/editinfo")
    public String updateCarInfo(@RequestBody Car entity) {
        if (carServ.update(entity) != null) {
            return "成功";
        } else {
            return "失败";
        }
    }

    /**
     * 删除司机
     * @param param
     * @return
     */
    @DeleteMapping(value = "/carinfo/delete/{id}")
    public String deleteDriver(@PathVariable("id") Long param) {
        if (carServ.delete(param)) {
            return "成功";
        } else {
            return "失败";
        }
    }

}
