package com.commsult.backend;

import com.commsult.files.BackEndApplication;
import com.commsult.files.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@BootstrapWith(value= SpringBootTestContextBootstrapper.class)
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@Rollback(false)
public class UserControllerTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Transactional
    public void testCreateUser(){
        User user = new User();
        user.setEmail("aaron@gmail.com");
        user.setUsername("aaron");
        user.setPassword("aaron123");
        try {
            ResponseEntity<User> response = restTemplate.postForEntity("/api/v1/register", user, User.class);
            User existUser = testEntityManager.find(User.class, response.getBody().getId());
            assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
        }catch (Exception e){
            System.out.println("Username or Email already exist");
        }
    }


    @Test
    public void testLoginUser(){
        User user = new User();
        user.setUsername("aaron");
        user.setPassword("aaron123");
        ResponseEntity<List> response = restTemplate.postForEntity("/api/v1/login", user, List.class);
        assertThat(response.getBody().get(1)).isEqualTo(true);
    }
}
