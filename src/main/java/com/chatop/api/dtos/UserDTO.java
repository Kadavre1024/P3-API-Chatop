package com.chatop.api.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	
	private String email;
	private String name;
	private String password;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
}
