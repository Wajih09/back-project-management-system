package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.controller.api.MessageApi;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Chat;
import com.siaw.projectmanagementsystem.model.Message;
import com.siaw.projectmanagementsystem.request.CreateMessageRequest;
import com.siaw.projectmanagementsystem.service.AppUserService;
import com.siaw.projectmanagementsystem.service.MessageService;
import com.siaw.projectmanagementsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController implements MessageApi {

    @Autowired
    private MessageService messageService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ProjectService projectService;

    @Override
    public ResponseEntity<Message> sendMessage(CreateMessageRequest req) throws Exception {
        AppUser user = appUserService.findUserById(req.getSenderId());
        //Chat chat = projectService.getChatByProjectId(req.getProjectId());
        Chat chat = projectService.getProjectById(req.getProjectId()).getChat(); //4h47min
        if(chat == null){
            throw new Exception("Chat not found !");
        }
        Message sentMessage = messageService.sendMessage(req.getSenderId(),
                req.getProjectId(), req.getContent());

        return ResponseEntity.ok(sentMessage);
    }

    @Override
    public ResponseEntity<List<Message>> getMessageByChatId(Long projectId) throws Exception {
        List<Message> messages = messageService.getMessageByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
