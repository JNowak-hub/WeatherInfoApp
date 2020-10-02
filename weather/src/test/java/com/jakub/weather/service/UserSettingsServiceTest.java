package com.jakub.weather.service;

import com.jakub.weather.configuration.CustomAuthorizationManager;
import com.jakub.weather.model.weather.user.Role;
import com.jakub.weather.model.weather.user.RoleEnum;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.model.weather.user.UserSettingsEntity;
import com.jakub.weather.repo.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
