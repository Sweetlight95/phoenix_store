package com.phoenix.data.repository;

import com.phoenix.data.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = "/db/insert.sql")
@Slf4j
class AppUserRepositoryTest {

    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("Create a new user with cart test")
//    @Transitional
    void whenNewUserIsCreated_thenCreateCartTest(){
        //creating a user object
        AppUser appUser = new AppUser();
        appUser.setFirstName("steve");
        appUser.setLastName("sing");
        appUser.setAddress("Ikoyi lagos");
        appUser.setEmail("steve@yahoo.co.uk");
        //save user
        assertThat(appUser.getId()).isNotNull();
        assertThat(appUser.getMyCart()).isNotNull();

        log.info("App user created :: {}", appUser);
    }
}