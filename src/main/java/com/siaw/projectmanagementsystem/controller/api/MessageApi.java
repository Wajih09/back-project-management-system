package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.model.Message;
import com.siaw.projectmanagementsystem.request.CreateMessageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/messages")
public interface MessageApi {

    @PostMapping("/send")
    ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest req) throws Exception;

    @GetMapping("/chat/{projectId}")
    ResponseEntity<List<Message>> getMessageByChatId(@PathVariable Long projectId) throws Exception;

}
