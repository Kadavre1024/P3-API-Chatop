package com.chatop.api.dtos;

import java.time.LocalDateTime;

import lombok.Data;



@Data
public class RentalDTO {

	private Long id;
	private String name;
	private Float surface;
	private Float price;
	private String picture;
	private String description;

	private Long owner_id;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;
}
