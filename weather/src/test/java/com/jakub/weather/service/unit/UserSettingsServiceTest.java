package com.jakub.weather.service.unit;

import com.jakub.weather.exceptions.UserAlreadyExists;
import com.jakub.weather.exceptions.UserNotFoundException;
import com.jakub.weather.exceptions.UserSettingsNotFoundException;
import com.jakub.weather.exceptions.WrongInputException;
import com.jakub.weather.model.weather.dto.UserSettingRequest;
import com.jakub.weather.model.weather.user.Role;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.model.weather.user.UserSettingsEntity;
import com.jakub.weather.repo.UserRepo;
import com.jakub.weather.repo.UserSettingsEntiyRepo;
import com.jakub.weather.service.*;
import com.jakub.weather.utils.UserSettingMapper;
import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserSettingsServiceTest {
    private UserSettingMapper settingMapper;
    private UserSettingsEntiyRepo settingsRepo;
    private UserService userService;
    private UserSettingsService settingsService;
    UserEntity user = new UserEntity("username", "password");

    @Test
    void when_deleteUserSettings_then_SettingsRepoDelete() {
        //given
        mockSetup();
        user.setId(1L);
        when(userService.findUserByUsername(user.getUsername())).thenReturn(user);
        //when
        settingsService.deleteUserSettings(user);
        //then
        verify(settingsRepo).deleteUserSettings(user.getId());
    }

    @Test
    void when_deleteUserSettings_then_throwUserNotFoundException() {
        mockSetup();
        when(userService.findUserByUsername(anyString())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> settingsService.deleteUserSettings(user));
    }

    @Test
    void when_getUserSettings_then_returnSettings() {
        //given
        mockSetup();
        UserSettingsEntity settings = new UserSettingsEntity();
        settings.setDefaultCity("Wroclaw");
        settings.setUser(user);
        user.setId(1L);
        when(settingsRepo.getUserSettingsByUserId(user.getId())).thenReturn(Optional.of(settings));
        //when
        UserSettingsEntity userSetting = settingsService.getUserSetting(user);
        //then
        assertThat(userSetting).isEqualTo(settings);
    }

    @Test
    void when_getUserSettings_then_throwUserSettingsNotFoundException() {
        //given
        mockSetup();
        user.setId(1L);
        when(settingsRepo.getUserSettingsByUserId(user.getId())).thenReturn(Optional.empty());
        //when
        UserSettingsNotFoundException exception = assertThrows(UserSettingsNotFoundException.class, () -> settingsService.getUserSetting(user));
        //then
        assertThat(exception.getMessage()).isEqualTo("Settings for user with id " + user.getId() + " not found");
    }

    @Test
    void when_updateUserSettings_then_updateSettings() {
        //given
        mockSetup();
        user.setId(1L);
        setSecurityContext();
        when(userService.findUserByUsername(user.getUsername())).thenReturn(user);
        UserSettingRequest request = new UserSettingRequest();
        //when
        settingsService.updateUserSettings(request);
        //then
        verify(settingMapper).changeSettings(request, user);
        verify(userService).saveUser(user);
    }
    @Test
    void when_updateUserSettings_then_throwUserNotFoundException(){
        mockSetup();
        setSecurityContext();
        UserSettingRequest request = new UserSettingRequest();
        when(userService.findUserByUsername(user.getUsername())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, ()-> settingsService.updateUserSettings(request));
    }

    private void setSecurityContext() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        Authentication auth = new
                UsernamePasswordAuthenticationToken(user, user.getPassword(), grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void mockSetup() {
        settingMapper = mock(UserSettingMapper.class);
        settingsRepo = mock(UserSettingsEntiyRepo.class);
        userService = mock(UserService.class);
        settingsService = new UserSettingsService(settingMapper, settingsRepo, userService);
    }

}
