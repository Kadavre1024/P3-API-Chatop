package com.chatop.api.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chatop.api.dtos.MessageDTO;
import com.chatop.api.dtos.MessageRequest;
import com.chatop.api.model.Message;
import com.chatop.api.repository.MessageRepository;

import lombok.Data;


public interface MessageService {
	
	public MessageDTO createNewMessage(MessageRequest m);
}
