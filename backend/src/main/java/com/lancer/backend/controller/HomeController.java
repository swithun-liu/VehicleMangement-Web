package com.lancer.backend.controller;

import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lancer.backend.Dao.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {
    @Autowired
    private LoginRepository loginRepository;

    /**登录 */

    @PostMapping(value = "/")
    public String login(@RequestBody Map<String, String> map) {
        String id = map.get("username").toString();
        String password = map.get("password").toString();
        if (!loginRepository.existsById(id)) {
            return "失败";
        } else if (loginRepository.getOne(id).getPassword().equals(password)) {
            String token = "";
            token = JWT.create().withAudience(id).sign(Algorithm.HMAC256(password));
            return token;
        } else {
            return "失败";
        }

    }
}