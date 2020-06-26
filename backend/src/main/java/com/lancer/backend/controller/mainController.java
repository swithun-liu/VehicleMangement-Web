package com.lancer.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {
    @GetMapping("/main")
    public String maintest(){
        return "dfdfdf";
    }
}