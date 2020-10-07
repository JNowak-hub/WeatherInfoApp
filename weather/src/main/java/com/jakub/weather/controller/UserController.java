package com.jakub.weather.controller;

import com.jakub.weather.model.weather.dto.UserSettingRequest;
import com.jakub.weather.service.UserService;
import com.jakub.weather.service.UserSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserSettingsService userSettingsService;

    public UserController(UserSettingsService userSettingsService) {
        this.userSettingsService = userSettingsService;
    }

    @PostMapping("/update/Settings")
    public ResponseEntity.BodyBuilder updateUserSettings(@RequestBody UserSettingRequest request){
        userSettingsService.updateUserSettings(request);
        return ResponseEntity.status(HttpStatus.OK);
    }
}
