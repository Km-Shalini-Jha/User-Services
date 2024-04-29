package com.user.service.services.External;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.service.Payload.CommonApiResponse;
import com.user.service.Payload.RatingResponse;


@FeignClient(name="RATING-SERVICE")
public interface RatingServices {
	
	@GetMapping("/ratings/getRatingForUser/{userId}")
	public ResponseEntity<CommonApiResponse<List<RatingResponse>>> getRatingsByUserId(@PathVariable("userId") String userId);
	
	
	@PutMapping("/ratings/updateRating")
	public ResponseEntity<CommonApiResponse<RatingResponse>> updateRating(@RequestParam("ratingId") String ratingId);
}
