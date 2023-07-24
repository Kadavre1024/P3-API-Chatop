package com.chatop.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dtos.RentalDTO;
import com.chatop.api.dtos.RentalRequest;
import com.chatop.api.dtos.UserDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository props;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public RentalDTO getRentalById(final Long id){		
		return copyRentalToDTO(props.findById(id).orElse(null));
	}
	
	@Override
	public List<Rental> getAllRentals(){
		if (props.findAll()!= null)
			return props.findAll();
		return null;
	}
	
	@Override
	public RentalDTO updateRental(RentalRequest r, Long id) {
		RentalDTO rental = getRentalById(id);
		rental.setName(r.getName());
		rental.setSurface(r.getSurface());
		rental.setPrice(r.getPrice());
		rental.setPicture(r.getPicture());
		rental.setDescription(r.getDescription());
		rental.setUpdatedAt(LocalDateTime.now());
		props.save(modelMapper.map(rental, Rental.class));
		return rental;
	}
	
	@Override
	public RentalDTO createRental(RentalRequest r, Long ownerId) {
		Rental rental = new Rental();
		rental.setName(r.getName());
		rental.setSurface(r.getSurface());
		rental.setPrice(r.getPrice());
		rental.setOwnerId(ownerId);
		rental.setDescription(r.getDescription());
		rental.setCreatedAt(LocalDateTime.now());
		rental.setUpdatedAt(LocalDateTime.now());
		rental.setPicture(r.getPicture());
		props.save(rental);		
		
		return copyRentalToDTO(rental);
	}
	
	@Override
	public RentalDTO copyRentalToDTO(Rental r) {
		RentalDTO rentalDTO = new RentalDTO();
		rentalDTO.setId(r.getId());
		rentalDTO.setName(r.getName());
		rentalDTO.setSurface(r.getSurface());
		rentalDTO.setPrice(r.getPrice());
		rentalDTO.setPicture(r.getPicture());
		rentalDTO.setDescription(r.getDescription());
		rentalDTO.setOwnerId(r.getOwnerId());
		rentalDTO.setCreatedAt(r.getCreatedAt());
		rentalDTO.setUpdatedAt(r.getUpdatedAt());
		return rentalDTO;
	}
	
	@Override
	public Rental saveRental(Rental r) {
		return props.save(r);
	}
	
	@Override
	public void deleteRentalById(final long id) {
		props.deleteById(id);
	}
	
	@Override
	public Long rentalsCount() {
		return props.count();
	}
}
