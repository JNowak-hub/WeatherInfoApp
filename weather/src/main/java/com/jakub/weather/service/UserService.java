package com.jakub.weather.service;

import com.jakub.weather.exceptions.UserAlreadyExists;
import com.jakub.weather.model.weather.user.Role;
import com.jakub.weather.model.weather.user.UserEntity;
import com.jakub.weather.model.weather.user.UserSettingsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.jakub.weather.repo.UserRepo;

import javax.persistence.Access;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    private UserLoggService loggService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserService(UserRepo userRepo, UserLoggService loggService) {
        this.userRepo = userRepo;
        this.loggService = loggService;
    }

    public UserEntity findById(Long id){
        return userRepo.findById(id).get();
    }

    public UserEntity createNewUser(UserEntity userEntity){

        if(findUserByUsername(userEntity.getUserName()).isPresent()){
            throw new UserAlreadyExists("user " + userEntity.getUserName() + " already exists, pick other userName");
        }
        if(userEntity.getPassword().isEmpty()){
            throw new RuntimeException("Password cannot be blank");
        }

        UserSettingsEntity defaultSetting = new UserSettingsEntity();
        defaultSetting.setDaysAmount(1L);
        defaultSetting.setDefaultCity("Katowice");

        UserEntity newUser = new UserEntity(userEntity.getUserName(), encoder.encode(userEntity.getPassword()));
        newUser.getRole().add(Role.USER);
        newUser.setSettings(defaultSetting);

        userRepo.save(newUser);
        loggService.userCreated(newUser);
        return newUser;
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
