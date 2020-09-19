package com.jakub.weather.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakub.weather.model.weather.WeatherResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class WeatherApiConnector {

    private final HttpClient httpClient;


    public WeatherApiConnector() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public Optional<WeatherResponse> weatherToClass(String cityName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://api.openweathermap.org/data/2.5/weather?q="+ cityName +"&appid=8af1f753ac7c4d67cd7987b1c374e618"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return Optional.ofNullable(mapper.readValue(response.body(), WeatherResponse.class));
    }
}
