package com.jakub.weather.service.unit;
import com.jakub.weather.exceptions.WrongInputException;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.repo.UserApiCallHistoryRepo;
import com.jakub.weather.service.UserApiCAllHistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserCallApiHistoryServiceTest {

    private UserApiCAllHistoryService apiCAllHistoryService;
    private UserApiCallHistoryRepo historyRepo;
    private UserEntity user = new UserEntity("username", "password");
    private String data = "Some data";

    @Test
    void when_callApi_then_historyRepoSave(){
        //given
        historyRepo = mock(UserApiCallHistoryRepo.class);
        apiCAllHistoryService = new UserApiCAllHistoryService(historyRepo);
        //when
        apiCAllHistoryService.callApi(data, user);
        //then
        verify(historyRepo).save(any());
    }

    @Test
    void when_callApiWithNullData_then_throw_WrongInputException(){
        //given
        historyRepo = mock(UserApiCallHistoryRepo.class);
        apiCAllHistoryService = new UserApiCAllHistoryService(historyRepo);
        data = null;
        //when
        WrongInputException exception = assertThrows(WrongInputException.class, () -> apiCAllHistoryService.callApi(data, user));
        //then
        assertThat(exception.getMessage()).isEqualTo("data cannot be empty or blank");
    }

    @Test
    void when_callApiWithBlankData_then_throw_WrongInputException(){
        //given
        historyRepo = mock(UserApiCallHistoryRepo.class);
        apiCAllHistoryService = new UserApiCAllHistoryService(historyRepo);
        data = "    ";
        //when
        WrongInputException exception = assertThrows(WrongInputException.class, () -> apiCAllHistoryService.callApi(data, user));
        //then
        assertThat(exception.getMessage()).isEqualTo("data cannot be empty or blank");
    }

    @Test
    void when_callApiWithEmptyData_then_throw_WrongInputException(){
        //given
        historyRepo = mock(UserApiCallHistoryRepo.class);
        apiCAllHistoryService = new UserApiCAllHistoryService(historyRepo);
        data = "";
        //when
        WrongInputException exception = assertThrows(WrongInputException.class, () -> apiCAllHistoryService.callApi(data, user));
        //then
        assertThat(exception.getMessage()).isEqualTo("data cannot be empty or blank");
    }
}
