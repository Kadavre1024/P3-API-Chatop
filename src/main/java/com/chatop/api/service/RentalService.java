package com.chatop.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {

	private RentalRepository props;
	
	public Optional<Rental> getRentalById(final Long id){
		return props.findById(id);
	}
	
	public Iterable<Rental> getAllRentals(){
		if (props.findAll()!= null)
			return props.findAll();
		return null;
	}
	
	public Rental saveRental(Rental r) {
		return props.save(r);
	}
	
	public void deleteRentalById(final long id) {
		props.deleteById(id);
	}
	
}
