package com.jakub.weather.controller;

import com.jakub.weather.model.weather.authorization.AuthorizationRequest;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.service.LoginService;
import com.jakub.weather.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/login")
//@CrossOrigin({"http://localhost:3000","http://localhost:8080"})
public class LoginSingInController {


    private UserService service;
    private LoginService loginService;

    public LoginSingInController(UserService service, LoginService loginService) {
        this.service = service;
        this.loginService = loginService;
    }

    @ApiOperation(value = "Creates new account with username and password", notes = "Username and password needs to be provided here as JSON")
    @PostMapping("/signIn")
    public ResponseEntity<UserEntity> singIn(@RequestBody UserEntity user){

        UserEntity newUser = service.createNewUser(user);

        return ResponseEntity.ok(newUser);
    }

    @ApiOperation(value = "Authorization of User based on username and password", notes = "Username and password needs to be provided here as JSON")
    @SneakyThrows
    @PostMapping
    public ResponseEntity<HttpServletResponse> login(@RequestBody AuthorizationRequest authRequest, HttpServletResponse response){
        loginService.authorization(authRequest);
        response.setHeader("Location", "http://localhost:3000/Home");
        return ResponseEntity.ok(response);
    }
}
