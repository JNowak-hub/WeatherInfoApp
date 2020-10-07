package com.jakub.weather.service.unit;

import com.jakub.weather.exceptions.UserNotFoundException;
import com.jakub.weather.exceptions.UserSettingsNotFoundException;
import com.jakub.weather.model.weather.dto.UserSettingRequest;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.model.weather.user.UserSettingsEntity;
import com.jakub.weather.repo.UserSettingsEntiyRepo;
import com.jakub.weather.service.*;
import com.jakub.weather.utils.UserSettingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest

public class UserDetailsServiceImplTest {
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void when_loadUserByUserName_then_userServiceFindUserByUsername(){
        //given
        UserEntity user = new UserEntity("username", "password");
        mockSetUp();
        when(userService.findUserByUsername(user.getUsername())).thenReturn(user);
        //when
        UserEntity loadedUser = (UserEntity) userDetailsService.loadUserByUsername(user.getUserName());
        //then
        assertThat(loadedUser).isEqualTo(user);
    }

    @Test
    void when_loadUserByUserName_then_throwUserNotFoundException(){
        mockSetUp();
        when(userService.findUserByUsername(anyString())).thenThrow(UserNotFoundException.class);
        assertThrows( UserNotFoundException.class,() -> userDetailsService.loadUserByUsername("username"));
    }

    private void mockSetUp() {
        userService = mock(UserService.class);
        userDetailsService = new UserDetailsServiceImpl(userService);
    }
}
