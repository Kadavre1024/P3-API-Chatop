package com.chatop.api.dtos;

import java.util.List;

import lombok.Data;

@Data
public class RentalsList {

	private List<RentalDTO> rentals;

	public RentalsList(List<RentalDTO> rentals) {
		this.rentals = rentals;
	}
}
