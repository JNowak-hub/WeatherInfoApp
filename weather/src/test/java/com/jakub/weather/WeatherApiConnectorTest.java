package com.jakub.weather;

import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.utils.WeatherApiConnector;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

import java.io.IOException;

@SpringBootTest
public class WeatherApiConnectorTest {

    private WeatherApiConnector connector;

    @BeforeEach
    void init(){
        connector = new WeatherApiConnector();
    }

    @Test
    void mapInfoToClass() throws IOException, InterruptedException {
        WeatherResponse response = null;
        response = connector.weatherToClass("Katowice").get();
        Assert.assertNotNull(response);
    }
}
