package com.jakub.weather.service;

import com.jakub.weather.exceptions.WeatherNotFoundException;
import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.model.weather.Wind;
import com.jakub.weather.model.weather.dto.CrucialWeatherData;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.utils.WeatherApiWebClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrucialWeatherDataService {


    private WeatherApiWebClient webClient;

    private UserApiCAllHistoryService historyService;

    private UserService userService;

    public CrucialWeatherDataService(WeatherApiWebClient webClient, UserApiCAllHistoryService historyService, UserService userService) {
        this.webClient = webClient;
        this.historyService = historyService;
        this.userService = userService;
    }

    private Double getTemperature(String cityName){
        return getWeatherInfo(cityName).getTemperature();
    }
    private Integer getPressure(String cityName){
        return getWeatherInfo(cityName).getPressure();
    }
    private Integer getHumidity(String cityName){
        return getWeatherInfo(cityName).getHumidity();
    }

    private Wind getWindInformation(String cityName){
        return getWeatherInfo(cityName).getWindInfo();
    }

    public String getDataByType(String cityName, String dataType){
        String currentUsername = ((UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUserName();

        UserEntity user = userService.findUserByUsername(currentUsername);

        if(dataType.equals("temperature")){
            historyService.callApi("Temperature in " + cityName + " is " + getTemperature(cityName), user);
            return "Temperature in " + cityName + " is " + getTemperature(cityName);
        } else if(dataType.equals("humidity")){
            historyService.callApi("Humidity in " + cityName + " is " + getHumidity(cityName), user);
            return "Humidity in " + cityName + " is " + getHumidity(cityName);
        } else if(dataType.equals("pressure")){
            historyService.callApi("Pressure in " + cityName + " is " + getPressure(cityName), user);
            return "Pressure in " + cityName + " is " + getPressure(cityName);
        }
        else if(dataType.equals("wind")){
            historyService.callApi("Wind in " + cityName + " is " + getWindInformation(cityName), user);
            return "Wind in " + cityName + " is " + getWindInformation(cityName);
        } else
            throw new WeatherNotFoundException("Couldn't find data. Try to type correct information type.");
    }


    public CrucialWeatherData getWeatherInfo(String cityName){

        WeatherResponse weatherData = webClient.getDataFromApi(cityName);

        CrucialWeatherData weatherInfo = new CrucialWeatherData();
        weatherInfo.setHumidity(weatherData.getMain().getHumidity());
        weatherInfo.setPressure(weatherData.getMain().getPressure());
        weatherInfo.setTemperature(weatherData.getMain().getTemp());
        weatherInfo.setWindInfo(weatherData.getWind());

        return weatherInfo;
    }

}
