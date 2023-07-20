package com.chatop.api.dtos;

import java.awt.Image;

import lombok.Data;

@Data
public class RentalRequest {

	private String name;
	private int surface;
	private int price;
	private Image picture;
	private String description;
	
}
