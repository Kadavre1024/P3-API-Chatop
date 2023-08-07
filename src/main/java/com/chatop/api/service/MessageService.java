package com.chatop.api.service;

import com.chatop.api.dtos.MessageDTO;
import com.chatop.api.dtos.MessageRequest;

public interface MessageService {

	public MessageDTO createNewMessage(MessageRequest m);
}
