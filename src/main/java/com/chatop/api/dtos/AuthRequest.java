package com.chatop.api.dtos;

import lombok.Data;

@Data
public class AuthRequest {

	private String email;
	private String password;
}
