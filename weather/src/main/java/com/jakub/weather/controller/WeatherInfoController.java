package com.jakub.weather.controller;

import com.jakub.weather.model.weather.dto.CrucialWeatherData;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.service.CrucialWeatherDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherInfoController {

    CrucialWeatherDataService service;

    public WeatherInfoController(CrucialWeatherDataService service) {
        this.service = service;
    }

    @GetMapping("/data")
    public String getTemperature(@RequestParam String cityName, @RequestParam String dataType){
            return service.getDataByType(cityName, dataType);
    }

    @GetMapping("/data/default")
    public ResponseEntity<CrucialWeatherData> getDefaultWeatherData(){
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(service.getWeatherInfo(user.getSettings().getDefaultCity()));
    }

}
