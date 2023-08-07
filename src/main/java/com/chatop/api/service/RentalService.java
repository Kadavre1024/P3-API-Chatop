package com.chatop.api.service;

import com.chatop.api.dtos.RentalDTO;
import com.chatop.api.dtos.RentalRequest;
import com.chatop.api.dtos.RentalsList;
import com.chatop.api.model.Rental;


public interface RentalService {

	RentalDTO getRentalById(final Long id);

	RentalsList getAllRentals();

	Rental saveRental(Rental r);

	void deleteRentalById(final long id);

	RentalDTO createRental(RentalRequest r, Long ownerId);

	Long rentalsCount();

	RentalDTO copyRentalToDTO(Rental r);

	RentalDTO updateRental(RentalRequest r, Long id);

	Rental copyDTOToRental(RentalDTO rDTO);

	Long getLastRentalId();
}
