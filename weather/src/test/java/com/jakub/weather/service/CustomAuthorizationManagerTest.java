package com.jakub.weather.service;

import com.jakub.weather.configuration.CustomAuthorizationManager;
import com.jakub.weather.model.weather.user.RoleEnum;
import com.jakub.weather.model.weather.user.UserEntity;
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
public class CustomAuthorizationManagerTest {

    @Autowired
    CustomAuthorizationManager authorizationManager;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    private UserEntity user = new UserEntity();
    private final String USER_PASSWORD = "password";
    private final String USERNAME = "username";
    private final RoleEnum ROLE = RoleEnum.USER;


    @BeforeEach
    void init() {
        System.out.println("BEFORE");
        user.setPassword(USER_PASSWORD);

        user.setUserName(USERNAME);
        userService.createNewUser(user);
    }
    @AfterEach
    void cleanUp(){
        System.out.println("AFTER");
        userService.deleteUserByUserName(user.getUserName());
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
