package com.chatop.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;

import lombok.Data;

@Data
@Service
public class MessageService {
	
	private MessageRepository props;
	
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
