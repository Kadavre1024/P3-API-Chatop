package com.chatop.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dtos.RentalDTO;
import com.chatop.api.dtos.RentalRequest;
import com.chatop.api.dtos.RentalsList;
import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private RentalRepository props;

	@Override
	public RentalDTO getRentalById(final Long id){
		return copyRentalToDTO(props.findById(id).orElse(null));
	}

	@Override
	public RentalsList getAllRentals(){
		List<Rental> rentals = props.findAll();
		List<RentalDTO> rentalsDTO = new ArrayList<>();
		if (props.findAll()!= null) {
			for(Rental r : rentals) {
				rentalsDTO.add(copyRentalToDTO(r));
			}
			return new RentalsList(rentalsDTO);
		}
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
		rental.setUpdated_at(LocalDateTime.now());
		props.save(copyDTOToRental(rental));
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
		rental.setCreated_at(LocalDateTime.now());
		rental.setUpdated_at(LocalDateTime.now());
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
		rentalDTO.setOwner_id(r.getOwnerId());
		rentalDTO.setCreated_at(r.getCreated_at());
		rentalDTO.setUpdated_at(r.getUpdated_at());
		return rentalDTO;
	}

	@Override
	public Rental copyDTOToRental(RentalDTO rDTO) {
		Rental rental = new Rental();
		rental.setId(rDTO.getId());
		rental.setName(rDTO.getName());
		rental.setSurface(rDTO.getSurface());
		rental.setPrice(rDTO.getPrice());
		rental.setPicture(rDTO.getPicture());
		rental.setDescription(rDTO.getDescription());
		rental.setOwnerId(rDTO.getOwner_id());
		rental.setCreated_at(rDTO.getCreated_at());
		rental.setUpdated_at(rDTO.getUpdated_at());
		return rental;
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

	@Override
	public Long getLastRentalId() {
		List<Rental> rentalList  =  props.findAll();
		rentalList.sort(Comparator.comparing(Rental::getId).reversed());
		return rentalList.get(0).getId();
	}
}
