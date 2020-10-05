package com.jakub.weather.service.unit;

import com.jakub.weather.exceptions.RoleNotFoundException;
import com.jakub.weather.model.weather.user.Role;
import com.jakub.weather.repo.RoleRepo;
import com.jakub.weather.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RoleServiceTest {

    @Test
    void when_getRoleByName_than_returnRole(){

        Role role = new Role();
        role.setRole("User");

        RoleRepo repo = mock(RoleRepo.class);
        when(repo.findByName("User")).thenReturn(Optional.ofNullable(role));

        RoleService roleService = new RoleService(repo);

        Role roleByName = roleService.getRoleByName("User");

        assertThat(roleByName).isEqualTo(role);
    }

    @Test
    void when_getRoleByName_than_throwsRoleNotFoundException(){

        Role role = new Role();
        role.setRole("User");

        RoleRepo repo = mock(RoleRepo.class);
        when(repo.findByName("User")).thenReturn(Optional.ofNullable(null));

        RoleService roleService = new RoleService(repo);

        RoleNotFoundException roleNotFoundException = assertThrows(RoleNotFoundException.class, () -> roleService.getRoleByName("User"));

        assertThat(roleNotFoundException.getMessage()).isEqualTo("No such role as User");
    }

}
