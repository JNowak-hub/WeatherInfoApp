package com.jakub.weather.repo;

import com.jakub.weather.model.weather.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    @Query(value = "Select * from users where username=?1", nativeQuery = true)
    Optional<UserEntity> findByUsername(String username);
}
