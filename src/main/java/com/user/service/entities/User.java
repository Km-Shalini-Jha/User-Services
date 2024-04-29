package com.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.user.service.Payload.RatingResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="micro_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private String userId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email; 
	
	@Column(name="ABOUT")
	private String about;
	
	@Transient
	private List<RatingResponse> rating = new ArrayList<>();
	
}
