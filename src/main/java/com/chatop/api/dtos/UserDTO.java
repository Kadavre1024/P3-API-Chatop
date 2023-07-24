package com.chatop.api.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	
	private String email;
	private String name;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
