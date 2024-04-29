package com.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(){
		super("Resource not found at server");
	}
	
	public ResourceNotFoundException(String message){
		super(message);
	}
}
