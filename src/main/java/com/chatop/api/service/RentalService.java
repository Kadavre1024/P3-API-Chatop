package com.chatop.api.service;

import java.util.List;
import java.util.Optional;

import com.chatop.api.dtos.RentalDTO;
import com.chatop.api.dtos.RentalRequest;
import com.chatop.api.model.Rental;


public interface RentalService {
	
	RentalDTO getRentalById(final Long id);
	
	List<Rental> getAllRentals();
	
	Rental saveRental(Rental r);
	
	void deleteRentalById(final long id);
	
	RentalDTO createRental(RentalRequest r, Long ownerId);

	Long rentalsCount();

	RentalDTO copyRentalToDTO(Rental r);

	RentalDTO updateRental(RentalRequest r, Long id);
}
