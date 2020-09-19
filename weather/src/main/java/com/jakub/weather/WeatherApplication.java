package com.jakub.weather;

import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.utils.WeatherApiConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);

	}

}
