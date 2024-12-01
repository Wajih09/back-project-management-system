package com.siaw.projectmanagementsystem.service.impl;

import com.siaw.projectmanagementsystem.model.Chat;
import com.siaw.projectmanagementsystem.repository.ChatRepository;
import com.siaw.projectmanagementsystem.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatserviceImpl implements ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
