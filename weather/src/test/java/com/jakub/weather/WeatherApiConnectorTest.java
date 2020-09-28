package com.jakub.weather;

import com.jakub.weather.exceptions.WrongInputException;
import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.utils.WeatherApiWebClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherApiConnectorTest {

    @Autowired
    private WeatherApiWebClient connector;

    @Test
    public void Given_City_When_getDataFromApi_Than_return_notNull_response() throws IOException, InterruptedException {
        //given
        WeatherResponse response = null;
        //when
        response = connector.getDataFromApi("Katowice");
        //than
        assertThat(response).isNotNull();
    }

    @Test(expected = WrongInputException.class)
    public void Given_Blank_CityName_When_getDataFormApi_Than_Throw_WrongInputException(){
        //given
        String cityName = "   ";
        //when
        connector.getDataFromApi(cityName);
        //than throws exception
    }
    @Test(expected = WrongInputException.class)
    public void Given_Empty_CityName_When_getDataFormApi_Than_Throw_WrongInputException(){
        //given
        String cityName = "   ";
        //when
        connector.getDataFromApi(cityName);
        //than throws exception
    }

}
