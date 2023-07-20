package com.chatop.api.dtos;

import lombok.Data;

@Data
public class AuthToken {

	private String token;
	
	public AuthToken(String jwt) {
		this.token = jwt;
	}
}
