package com.chatop.api.dtos;

import lombok.Data;

@Data
public class RentalResponse {

	private String message;

	public RentalResponse(String message) {
		this.message = message;
	}
}
