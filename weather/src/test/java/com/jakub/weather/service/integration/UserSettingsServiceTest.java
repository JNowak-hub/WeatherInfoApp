package com.jakub.weather.service.integration;

import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.model.weather.user.UserSettingsEntity;
import com.jakub.weather.service.UserService;
import com.jakub.weather.service.UserSettingsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserSettingsServiceTest {

    @Autowired
    private UserSettingsService settingsService;

    @Autowired
    private UserService userService;

    private UserSettingsEntity userSettingsEntity;



    @Test
    public void Given_User_When_getUserSetting_Than_return_settings() {
        //given
        userSettingsEntity = new UserSettingsEntity();
        userSettingsEntity.setDefaultCity("Katowice");
        userSettingsEntity.setDaysAmount(3L);
        UserEntity user = userService.findUserByUsername("admin");
        //when
        UserSettingsEntity userSettings = settingsService.getUserSetting(user);
        //than
        assertThat(userSettings.getDefaultCity()).isEqualTo("Katowice");
    }
}
