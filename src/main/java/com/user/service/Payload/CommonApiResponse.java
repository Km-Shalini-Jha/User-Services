package com.user.service.Payload;

import lombok.Data;

@Data
public class CommonApiResponse<T> {
	private String success;
	private String message;
	private T Data;	
}