package com.jakub.weather.controller;

import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/login")
public class LoginSingInController {

    private UserService service;

    public LoginSingInController(UserService service) {
        this.service = service;
    }

    @PostMapping("/singIn")
    public ResponseEntity<UserEntity> singIn(@RequestBody UserEntity user){

        UserEntity newUser = service.createNewUser(user);

        return ResponseEntity.ok(newUser);
    }
}
