package com.onurfatih.chat.chatcraft.core.service.impl;

import com.onurfatih.chat.chatcraft.core.entity.Message;
import com.onurfatih.chat.chatcraft.core.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImp {

    @Autowired
    private MessageRepository messageRepository;

    public void insert(Message message) {
        messageRepository.save(message);
    }

}
