package com.jakub.weather.service;

import com.jakub.weather.model.weather.user.UserEntity;
import org.springframework.stereotype.Service;
import com.jakub.weather.repo.UserRepo;

import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity findById(Long id){
        return userRepo.findById(id).get();
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
