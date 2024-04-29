package com.user.service.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.net.httpserver.Headers;
import com.user.service.Payload.ApiResponse;
import com.user.service.entities.User;
import com.user.service.services.UserService;


@RestController
@RequestMapping("/users")
public class UserServiceController {
	
	@Autowired
	private UserService userService;
	
	//create user
	@PostMapping("/createUser")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		System.out.println("createUser Called!!");
		User userData = userService.saveUser(user);		
			
		HttpHeaders headers = new HttpHeaders();
		headers.set("user", "user data saved");
		headers.set("user name", userData.getName());
		return ResponseEntity.ok().headers(headers).body(userData);	
	}
	
	// get single user
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId){
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);	
	}
	
	// get all users
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
       String statusValue = userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse(statusValue,true,HttpStatus.MOVED_PERMANENTLY),HttpStatus.OK);		
	}
	
	@PatchMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestParam("usersId") String userId){
		User user = userService.updateUser(userId);
		return ResponseEntity.ok().body(user);		
	}
}
