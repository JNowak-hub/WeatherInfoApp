package com.jakub.weather.service;

import com.jakub.weather.configuration.CustomAuthorizationManager;
import com.jakub.weather.exceptions.UserNotFoundException;
import com.jakub.weather.exceptions.WeatherNotFoundException;
import com.jakub.weather.model.weather.WeatherInfo;
import com.jakub.weather.model.weather.WeatherResponse;
import com.jakub.weather.model.weather.Wind;
import com.jakub.weather.model.weather.dto.CrucialWeatherData;
import com.jakub.weather.model.weather.user.Role;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.utils.WeatherApiWebClient;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomAuthorizationManagerTest {

    @Autowired
    CustomAuthorizationManager authorizationManager;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    private static UserEntity user = new UserEntity();
    private static final String USER_PASSWORD = "password";
    private static final String USERNAME = "username";
    private static final Role ROLE = Role.USER;


    @BeforeAll
    public void init() {
        user.setPassword(passwordEncoder.encode(USER_PASSWORD));
        user.getRole().add(ROLE);
        user.setUserName(USERNAME);
        userService.createNewUser(user);
    }

    @Test
    public void Given_Authentication_When_authenticate_Then_Authenticate() {
        //given
        Authentication userToAuthenticate = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //when
        Authentication authentication = authorizationManager.authenticate(userToAuthenticate);
        //than
        assertThat(authentication.isAuthenticated()).isTrue();
    }

    @Test
    public void Given_Wrong_UserName_When_authenticate_Then_Throw_UserNotFoundException() {
        Authentication userToAuthenticate = new UsernamePasswordAuthenticationToken("WrongUserName", user.getPassword());
        //when
        Authentication authentication = authorizationManager.authenticate(userToAuthenticate);
        //than throw UserNotFoundException exception
    }

    @Test
    public void Given_Wrong_Password_When_authenticate_Then_Throw_BadCredentialsException() {
        Authentication userToAuthenticate = new UsernamePasswordAuthenticationToken(user.getUserName(), "wrongPassword");
        //when
        Authentication authentication = authorizationManager.authenticate(userToAuthenticate);
        //than throw UserNotFoundException exception
    }
}
