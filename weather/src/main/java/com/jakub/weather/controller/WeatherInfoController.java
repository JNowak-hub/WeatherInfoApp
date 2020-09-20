package com.jakub.weather.controller;

import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.service.CrucialWeatherDateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/weather")
public class WeatherInfoController {

    CrucialWeatherDateService service;

    public WeatherInfoController(CrucialWeatherDateService service) {
        this.service = service;
    }

    @GetMapping("/data")
    public String getTemperature(@RequestParam String cityName, @RequestParam String dataType){
            return service.getDataByType(cityName, dataType);
    }

}
