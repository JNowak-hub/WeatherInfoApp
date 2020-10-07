package com.jakub.weather.service.unit;

import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.service.LoginSucessHandler;
import com.jakub.weather.service.UserLoggService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LoginSucessHandlerTest {

    private UserLoggService loggService;
    private LoginSucessHandler loginSucessHandler;
    HttpServletRequest request;
    HttpServletResponse response;
    Authentication auth;
    private UserEntity user = new UserEntity("username", "password");

    @Test
    void when_onAuthenticationSuccess_thenLoggServiceloggLogin() throws ServletException, IOException {
        //given
        setUpMock();
        setUpContextHolder();
        //when
        loginSucessHandler.onAuthenticationSuccess(request,response,auth);
        //then
        verify(loggService).loggLogin(any());
    }


    private void setUpContextHolder() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), grantedAuthorities);
    }

    private void setUpMock(){
        loggService = mock(UserLoggService.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        loginSucessHandler = new LoginSucessHandler(loggService);
    }
}
