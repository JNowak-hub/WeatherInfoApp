package com.jakub.weather.service;

import com.jakub.weather.exceptions.UserNotFoundException;
import com.jakub.weather.exceptions.UserSettingsNotFoundException;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.model.weather.user.UserSettingsEntity;
import com.jakub.weather.repo.UserSettingsEntiyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSettingsService {

    private UserSettingsEntiyRepo settingsRepo;

    public UserSettingsService(UserSettingsEntiyRepo settingsRepo) {
        this.settingsRepo = settingsRepo;
    }

    public void deleteUserSettings(UserEntity user){
        if(user == null){
            throw new UserNotFoundException("User Not found");
        }
        settingsRepo.deleteUserSettings(user.getId());
    }

    public UserSettingsEntity getUserSetting(UserEntity user){
        Optional<UserSettingsEntity> optionalSettings = settingsRepo.getUserSettingsByUserId(user.getId());
        if(optionalSettings.isEmpty()){
            throw new UserSettingsNotFoundException("Settings for user with id " + user.getId() + " not found");
        }
        return optionalSettings.get();
    }

}
