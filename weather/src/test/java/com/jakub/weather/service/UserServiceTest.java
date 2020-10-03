package com.jakub.weather.service;

import com.jakub.weather.configuration.CustomAuthorizationManager;
import com.jakub.weather.model.weather.user.Role;
import com.jakub.weather.model.weather.user.RoleEnum;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.repo.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;

    private UserEntity user = new UserEntity();
    private final String USER_PASSWORD = "password";
    private final String USERNAME = "username";
    private final String ROLE = "USER";

    @BeforeEach
    void tearDown(){
        userRepo.deleteAll();
    }

    @Test
    void Given_User_When_createNewUser_Than_newUser_in_DataBase(){
        //given
        user.setPassword(USER_PASSWORD);
        user.setUserName(USERNAME);
        //When
        userService.createNewUser(user);
        //THan
        assertThat(userService.findUserByUsername(user.getUserName()).getUserName()).isEqualTo(user.getUserName());
    }
}
