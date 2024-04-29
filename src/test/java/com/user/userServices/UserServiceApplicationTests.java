package com.user.userServices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.user.service.UserServiceApplication;
import com.user.service.Payload.RatingResponse;
import com.user.service.entities.User;
import com.user.service.repositories.UserRepository;


@SpringBootTest(classes = UserServiceApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserServiceApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void testSaveUser() {
    	List<RatingResponse> rating = new ArrayList<>();   	
        User user = new User("1","John Doe", "john@example.com","I am JAVA developer",rating);
        User userData = userRepository.save(user);
        System.out.println("userData "+userData);
        assertNotNull(user.getUserId());
    }
    
    @Test
    @Order(2)
    public void testFindUserById() {
        User user = userRepository.findById("1").orElse(null);
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
    }
    
    @Test
    @Order(3)
    public void testGetAllUser(){
        List<User> user = userRepository.findAll();
        Assertions.assertThat(user.size()).isGreaterThan(0);
    }


    @Test
    @Order(4)
    @Rollback(value = false)
    public void testUpdateUser() {
        User user = userRepository.findById("1").orElse(null);
        assertNotNull(user);
        user.setEmail("shalini@gmail.com");
        userRepository.save(user);
        
        User updatedUser = userRepository.findById("1").orElse(null);
        assertNotNull(updatedUser);
        assertEquals("shalini@gmail.com", updatedUser.getEmail());
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void testDeleteUser(){

		User user = userRepository.findById("1").get();
		userRepository.delete(user);
		User user1 = null;
		
		Optional<User> user2 = userRepository.findById("1");

        if(user2.isPresent()){
        	user1 = user2.get();
        }

        Assertions.assertThat(user1).isNull();
    }

}
