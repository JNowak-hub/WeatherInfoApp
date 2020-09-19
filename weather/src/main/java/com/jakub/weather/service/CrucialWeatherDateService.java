package com.jakub.weather.service;

import com.jakub.weather.exceptions.WeatherNotFoundException;
import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.model.weather.Wind;
import com.jakub.weather.model.weather.dto.CrucialWeatherData;
import com.jakub.weather.utils.WeatherApiConnector;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CrucialWeatherDateService {

    private WeatherApiConnector connector;

    public CrucialWeatherDateService(WeatherApiConnector connector) {
        this.connector = connector;
    }

    public Double getTemperature(String cityName){
        CrucialWeatherData data = getWeatherInfo(cityName);
        return data.getTemperature();
    }
    public Integer getPressure(String cityName){
        CrucialWeatherData data = getWeatherInfo(cityName);
        return data.getPressure();
    }
    public Integer getHumidity(String cityName){
        CrucialWeatherData data = getWeatherInfo(cityName);
        return data.getHumidity();
    }

    public Wind getWindInformation(String cityName){
        CrucialWeatherData data = getWeatherInfo(cityName);
        return data.getWindInfo();
    }

    public String getDataByType(String cityName, String dataType){
        if(dataType.equals("temperature"))
            return "Temperature in " + cityName + " is " + getTemperature(cityName);
        else if(dataType.equals("humidity"))
            return "Humidity in " + cityName + " is " + getHumidity(cityName);
        else if(dataType.equals("pressure"))
            return "Pressure in " + cityName + " is " + getPressure(cityName);
        else if(dataType.equals("wind"))
            return "Wind in " + cityName + " is " + getWindInformation(cityName);
        else
            throw new WeatherNotFoundException("Couldn't find data. Try to type correct input.");
    }


    public CrucialWeatherData getWeatherInfo(String cityName){

        Optional<WeatherResponse> optionalCrucialWeatherData = null;
        try {
            optionalCrucialWeatherData = connector.weatherToClass(cityName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(optionalCrucialWeatherData.isEmpty()){
            throw new WeatherNotFoundException("Couldn't load weather information");
        }
        WeatherResponse response = optionalCrucialWeatherData.get();
        CrucialWeatherData data = new CrucialWeatherData();
        data.setHumidity(response.getMain().getHumidity());
        data.setPressure(response.getMain().getPressure());
        data.setTemperature(response.getMain().getTemp());
        data.setWindInfo(response.getWind());

        return data;
    }

}
