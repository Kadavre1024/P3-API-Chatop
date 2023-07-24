package com.chatop.api.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDTO {

	private Long id;
	
	private Long rentalId;
	
	private Long userId;
	private String message;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}
