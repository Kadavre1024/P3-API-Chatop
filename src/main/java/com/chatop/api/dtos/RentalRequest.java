package com.chatop.api.dtos;


import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RentalRequest {

	private String name;
	private float surface;
	private float price;
	private String picture;
	private String description;
	
	public RentalRequest() {
		super();
	}
	
}
