package com.jakub.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakub.weather.configuration.CustomExceptionHandler;
import com.jakub.weather.exceptions.UserAlreadyExists;
import com.jakub.weather.exceptions.WrongInputException;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.service.LoginService;
import com.jakub.weather.service.UserService;
import com.jakub.weather.service.UserSettingsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserSettingsService settingsService;
    @InjectMocks
    private UserController controller;


}
