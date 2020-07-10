package com.lancer.backend.controller;

import java.util.Date;
import java.util.List;

import com.lancer.backend.entity.Dispatch;
import com.lancer.backend.service.Impl.DispatchServImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatchInfoController {
    
    @Autowired
    private DispatchServImpl dispatchServImpl;

    /***
     * 根据参数获取调度单信息
     * 
     * @param info
     * @return
     */
    @GetMapping("/dispatchinfo")
    public List<Dispatch> findAll(@RequestParam(name = "query") String info){
        if(info.length()!=0){
            return dispatchServImpl.findByDispatchIdLike(info);
        }else{
            return dispatchServImpl.findAll();
        }
    }

    /**
     * 添加调度单
     * @param entity
     * @return
     */
    @PostMapping(value="/dispatchinfo/adddispatchinfo")
    public String AddOne(@RequestBody Dispatch entity) {
        String dispatchId = String.valueOf(new Date().getTime());
        entity.setDispatchId(dispatchId);
        if(dispatchServImpl.AddOne(entity)!=null){
            return "成功";
        }else{
            return "失败";
        }
    }

    /**
     * 根据id获取调度单信息
     * @param param
     * @return
     */
    @GetMapping(value="/dispatchinfo/edit/{id}")
    public Dispatch getOneforEdit(@PathVariable("id") String param) {
        if(param == null){
            return null;
        }else{
            return dispatchServImpl.getOnebyId(param);
        }
    }

    /**
     * 编辑调度单信息
     * @param entity
     * @return
     */
    @PostMapping(value="/dispatchinfo/editinfo")
    public String updateDispatchInfo(@RequestBody Dispatch entity) {
        if(dispatchServImpl.update(entity) !=null){
            return "成功";
        }else{
            return "失败";
        }
    }
    
    /**
     * 删除调度单
     * @param param
     * @return
     */
    @DeleteMapping(value="/dispatchinfo/delete/{id}")
    public String deleteDispatch(@PathVariable("id") String param){
        if(dispatchServImpl.delete(param)){
            return "成功";
        }else{
            return "失败";
        }
    }
}