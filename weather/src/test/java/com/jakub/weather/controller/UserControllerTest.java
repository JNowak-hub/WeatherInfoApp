package com.jakub.weather.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakub.weather.configuration.CustomExceptionHandler;
import com.jakub.weather.model.weather.dto.UserSettingRequest;
import com.jakub.weather.service.UserService;
import com.jakub.weather.service.UserSettingsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    @Mock
    private UserSettingsService settingsService;
    @InjectMocks
    private UserController controller;

    @BeforeEach
    void mockSetup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void when_updateUserSettings_returnStatusOk() throws Exception {
        UserSettingRequest request = new UserSettingRequest();
        request.setDaysAmount(2L);
        request.setDefaultCity("Krakow");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/user/update/Settings")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.defaultCity", is("Krakow")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysAmount", is(2)));
    }
}
