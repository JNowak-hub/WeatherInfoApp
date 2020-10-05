package com.jakub.weather.service.unit;
import com.jakub.weather.configuration.CustomAuthorizationManager;
import com.jakub.weather.model.weather.authorization.AuthorizationRequest;
import com.jakub.weather.service.LoginService;
import com.jakub.weather.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class LoginServiceTest {
    @Test
    void when_authorization_then_invoke_authenticationManager_authenticate(){
        AuthenticationManager authorizationManager = mock(CustomAuthorizationManager.class);
        LoginService loginService = new LoginService(authorizationManager);
        AuthorizationRequest authRequest = new AuthorizationRequest();
        authRequest.setPassword("password");
        authRequest.setUserName("userName");
        loginService.authorization(authRequest);

        verify(authorizationManager).authenticate(any());
    }

    @Test
    void when_authorization_then_throw_BadCredentialsException(){
        AuthenticationManager authorizationManager = mock(CustomAuthorizationManager.class);
        when(authorizationManager.authenticate(any())).thenThrow(new BadCredentialsException("Bad credentials"));
        LoginService loginService = new LoginService(authorizationManager);
        AuthorizationRequest authRequest = new AuthorizationRequest();
        authRequest.setPassword("password");
        authRequest.setUserName("userName");
        BadCredentialsException badCredentialsException = assertThrows(BadCredentialsException.class, () -> loginService.authorization(authRequest));
        assertThat(badCredentialsException.getMessage()).isEqualTo("Bad credentials");
    }
}
