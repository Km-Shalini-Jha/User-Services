package com.user.service.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.user.service.Payload.CommonApiResponse;
import com.user.service.Payload.Hotel;
import com.user.service.Payload.RatingResponse;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import com.user.service.services.External.HotelServices;
import com.user.service.services.External.RatingServices;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HotelServices hotelServices;
	
	@Autowired
	private RatingServices ratingServices;
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Override
	public User saveUser(User user) {
		//generate unique userId
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> user = userRepository.findAll();
		System.out.println(user);
		return user;
	}

	@Override
	public User getUser(String userId) {
		
		Optional<User> user = userRepository.findById(userId);
		User userData = new User();
		
		if(ObjectUtils.isEmpty(user)) {			
			throw new ResourceNotFoundException("user with given id is not available at server !! "+userId);				
		}
				
		userData.setEmail(user.get().getEmail());
		userData.setAbout(user.get().getAbout());
		userData.setName(user.get().getName());
		userData.setUserId(user.get().getUserId());	
		
		
		// calling RAtingservices through FEIGN CLIENT
		ResponseEntity<CommonApiResponse<List<RatingResponse>>> response = ratingServices.getRatingsByUserId(userData.getUserId());
		List<RatingResponse> ratingResp = response.getBody().getData();
		
		for(RatingResponse list : ratingResp) {			
			 ResponseEntity<CommonApiResponse<Hotel>> hotelResp = hotelServices.getSingleHotel(list.getHotelId());
			 Hotel hotelData = hotelResp.getBody().getData();			 
			 list.setHotel(hotelData);			 
		}
					
		userData.setRating(ratingResp);
		
		return userData;
	}

	@Override
	public String deleteUser(String userId) {	
		//check whether user id is present or not
		User user = getUser(userId);
		
		HttpHeaders header = new HttpHeaders();
		String statusValue ="";
		
		if(user != null) {
			userRepository.deleteById(userId);
			header.set("status", "user deleted successfully");
			statusValue ="user deleted";
		}else {
			header.set("status", "No user present for id "+userId);
			statusValue = "User Id is not present";
		}
		
		return statusValue;
	}

	@Override
	public User updateUser(String userId) {
		//check whether user id is present or not
		  User user = getUser(userId);
		  User userData = null;
		  
		  if(user != null) {
			  user.setName("ANNY");
			  userData = userRepository.save(user);
		  }
		  
		return userData;
	}

}
