package com.chatop.api.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.api.dtos.MessageDTO;
import com.chatop.api.dtos.MessageRequest;
import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageRepository props;

	@Override
	public MessageDTO createNewMessage(MessageRequest m) {
		Message message = new Message();
		message.setMessage(m.getMessage());
		message.setUser_id(m.getUser_id());
		message.setRental_id(m.getRental_id());
		message.setCreated_at(LocalDateTime.now());
		message.setUpdated_at(LocalDateTime.now());
		props.save(message);
		return modelMapper.map(message, MessageDTO.class);
	}

	public Iterable<Message> getAllMessages(){
		return props.findAll();
	}

	public Optional<Message> getMessageById(final long id){
		return props.findById(id);
	}

	public Message saveMessage(Message m) {
		return props.save(m);
	}

	public void deleteMessageById(final long id) {
		props.deleteById(id);
	}
}
