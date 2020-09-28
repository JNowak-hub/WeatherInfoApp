package com.jakub.weather.service;

import com.jakub.weather.exceptions.WeatherNotFoundException;
import com.jakub.weather.model.weather.WeatherInfo;
import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.model.weather.Wind;
import com.jakub.weather.model.weather.dto.CrucialWeatherData;
import com.jakub.weather.utils.WeatherApiWebClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CrutialWeatherDataServiceTest {

    private static final double WIND_DEG = 1.0;
    private static final double WIND_SPEED = 50.0;
    private static final int PRESSURE = 15;
    private static final int HUMIDITY = 22;
    private static final int TEMPERATURE = 11;

    @Autowired
    private UserApiCAllHistoryService historyService;

    @Autowired
    private UserService userService;

    @Test
    public void Given_CityName_When_GetWeatherInfo_Then_ReturnWeatherData(){
        //given
        WeatherApiWebClient webClientMock = mock(WeatherApiWebClient.class);
        WeatherResponse respone = createWeatherResponse();
        when(webClientMock.getDataFromApi(any())).thenReturn(respone);
        CrucialWeatherDataService service = new CrucialWeatherDataService(webClientMock, historyService, userService);
        //when
        CrucialWeatherData data = service.getWeatherInfo("Katowice");
        //than
        assertThat(data.getHumidity()).isEqualTo(HUMIDITY);
        assertThat(data.getPressure()).isEqualTo(PRESSURE);
        assertThat(data.getTemperature()).isEqualTo(TEMPERATURE);
    }

    @Test(expected = WeatherNotFoundException.class)
    public void Given_CityName_When_GetWeatherInfo_Then_Throw_WeatherNotFoundException(){
        //given
        WeatherApiWebClient webClientMock = mock(WeatherApiWebClient.class);
        when(webClientMock.getDataFromApi(any())).thenThrow(WeatherNotFoundException.class);
        CrucialWeatherDataService service = new CrucialWeatherDataService(webClientMock, historyService, userService);
        //when
        CrucialWeatherData data = service.getWeatherInfo("Katowice");
        //than throws exception

    }

    private WeatherResponse createWeatherResponse(){
        WeatherResponse response = new WeatherResponse();

        Wind wind = new Wind();
        wind.setDeg(WIND_DEG);
        wind.setSpeed(WIND_SPEED);
        response.setWind(wind);

        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setPressure(PRESSURE);
        weatherInfo.setHumidity(HUMIDITY);
        weatherInfo.setTemp(TEMPERATURE);
        response.setMain(weatherInfo);

        return response;
    }
}
