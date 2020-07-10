package com.lancer.backend.controller;

import java.util.List;

import com.lancer.backend.entity.Registration;
import com.lancer.backend.service.Impl.RegistrationServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RegistrationInfoController {
    
    @Autowired
    private RegistrationServImpl registrationServImpl;

    /***
     * 根据参数获取维修信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/registrationinfo")
    public List<Registration> findAll(@RequestParam(name = "query") String info){
        if(info.length()!=0){
            return registrationServImpl.findByDispatchIdLike(info);
        }else{
            return registrationServImpl.findAll();
        }
    }

    /**
     * 添加维修单
     * @param entity
     * @return
     */
    @PostMapping(value="/registrationinfo/addregistrationinfo")
    public String AddOne(@RequestBody Registration entity) {
        if(registrationServImpl.AddOne(entity)!=null){
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
    @GetMapping(value="/registrationinfo/edit/{id}")
    public Registration getOneforEdit(@PathVariable("id") String param) {
        if(param == null){
            return null;
        }else{
            return registrationServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑维修单信息
     * @param entity
     * @return
     */
    @PostMapping(value="/registrationinfo/editinfo")
    public String updateRegistrationInfo(@RequestBody Registration entity) {
        if(registrationServImpl.update(entity) !=null){
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
    @DeleteMapping(value="/registrationinfo/delete/{id}")
    public String deleteRegistration(@PathVariable("id") String param){
        if(registrationServImpl.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }
}