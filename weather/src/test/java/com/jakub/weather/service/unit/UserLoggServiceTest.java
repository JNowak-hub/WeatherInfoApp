package com.jakub.weather.service.unit;

import com.jakub.weather.exceptions.UserNotFoundException;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.repo.UserLoggEntityRepo;
import com.jakub.weather.service.UserLoggService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserLoggServiceTest {
    @Test
    void when_loggLogin_than_invokeLoggRepo_save(){
        UserLoggEntityRepo loggEntityRepo = mock(UserLoggEntityRepo.class);
        UserLoggService loggService = new UserLoggService(loggEntityRepo);

        UserEntity userEntity = new UserEntity("user", "user");

        loggService.loggLogin(userEntity);

        verify(loggEntityRepo).save(any());
    }


    @Test
    void when_loggLogin_withNoUser_than_throwUserNotFoundException(){
        UserLoggEntityRepo loggEntityRepo = mock(UserLoggEntityRepo.class);
        UserLoggService loggService = new UserLoggService(loggEntityRepo);

        UserEntity userEntity = null;


        UserNotFoundException myException = assertThrows(UserNotFoundException.class, () -> loggService.loggLogin(userEntity));

        assertThat(myException.getMessage()).isEqualTo("User cannot be null");
    }
    @Test
    void when_logOut_than_invokeLoggRepo_save(){
        UserLoggEntityRepo loggEntityRepo = mock(UserLoggEntityRepo.class);
        UserLoggService loggService = new UserLoggService(loggEntityRepo);

        UserEntity userEntity = new UserEntity("user", "user");

        loggService.logLogout(userEntity);

        verify(loggEntityRepo).save(any());
    }
    @Test
    void when_logOut_withNoUser_than_throwUserNotFoundException(){
        UserLoggEntityRepo loggEntityRepo = mock(UserLoggEntityRepo.class);
        UserLoggService loggService = new UserLoggService(loggEntityRepo);

        UserEntity userEntity = null;


        UserNotFoundException myException = assertThrows(UserNotFoundException.class, () -> loggService.logLogout(userEntity));

        assertThat(myException.getMessage()).isEqualTo("User cannot be null");
    }
    @Test
    void when_userCreated_than_invokeLoggRepo_save(){
        UserLoggEntityRepo loggEntityRepo = mock(UserLoggEntityRepo.class);
        UserLoggService loggService = new UserLoggService(loggEntityRepo);

        UserEntity userEntity = new UserEntity("user", "user");

        loggService.userCreated(userEntity);

        verify(loggEntityRepo).save(any());
    }
    @Test
    void when_userCreated_withNoUser_than_throwUserNotFoundException(){
        UserLoggEntityRepo loggEntityRepo = mock(UserLoggEntityRepo.class);
        UserLoggService loggService = new UserLoggService(loggEntityRepo);

        UserEntity userEntity = null;


        UserNotFoundException myException = assertThrows(UserNotFoundException.class, () -> loggService.userCreated(userEntity));

        assertThat(myException.getMessage()).isEqualTo("User cannot be null");
    }



}
