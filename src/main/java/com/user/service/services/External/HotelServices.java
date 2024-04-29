package com.user.service.services.External;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.service.Payload.CommonApiResponse;
import com.user.service.Payload.Hotel;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelServices {
	
	 @GetMapping("/hotel/getSingleHotel")
	 ResponseEntity<CommonApiResponse<Hotel>> getSingleHotel(@RequestParam("hotelId") String HotelId);
	
}
