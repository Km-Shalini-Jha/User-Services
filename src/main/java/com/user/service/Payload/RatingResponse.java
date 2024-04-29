package com.user.service.Payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {

	private String ratingID;
	private int rating;
	private String feedback;
	private String userId;
	private String hotelId;
	private Hotel hotel;
}
