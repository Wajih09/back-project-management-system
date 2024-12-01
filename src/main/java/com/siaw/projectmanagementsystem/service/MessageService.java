package com.siaw.projectmanagementsystem.service;

import com.siaw.projectmanagementsystem.model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;

    List<Message> getMessageByProjectId(Long projectId) throws Exception;
}
