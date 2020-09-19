package com.jakub.weather;

import com.jakub.weather.model.weather.dto.CrucialWeatherData;
import com.jakub.weather.service.CrucialWeatherDateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CrutialWeatherDataServiceTest {
    @Autowired
    private CrucialWeatherDateService service;

    @Test
    public void getCrucialDataInfo(){
        CrucialWeatherData data = service.getWeatherInfo("Katowice");
        Assert.assertNotNull(data);
    }
}
