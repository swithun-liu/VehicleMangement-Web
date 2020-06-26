package com.lancer.backend.controller;

import java.util.List;

import com.lancer.backend.entity.Login;
import com.lancer.backend.repository.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/book")
public class TestController {
    @Autowired
    private LoginRepository loginrepository;

    @GetMapping("/test")
    public List<Login> findAll(){
        return loginrepository.findAll();
    }
}