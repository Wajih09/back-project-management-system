package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.model.Chat;
import com.siaw.projectmanagementsystem.model.Invitation;
import com.siaw.projectmanagementsystem.model.Project;
import com.siaw.projectmanagementsystem.request.InviteRequest;
import com.siaw.projectmanagementsystem.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/projects")
public interface ProjectApi {

    @GetMapping
    ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @GetMapping("/{projectId}")
    ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @PostMapping
    ResponseEntity<Project> createProject(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project) throws Exception;

    @PatchMapping("/{projectId}")
    ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project) throws Exception;

    @DeleteMapping("/{projectId}")
    ResponseEntity<MessageResponse> deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @GetMapping("/search")
    ResponseEntity<List<Project>> searchProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @GetMapping("/{projectId}/chat")
    ResponseEntity<Chat> getChatProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @PostMapping("/invite")
    ResponseEntity<MessageResponse> inviteProject(
            @RequestHeader("Authorization") String jwt,
            @RequestBody InviteRequest req) throws Exception;

    @GetMapping("/accept_invitation")
    ResponseEntity<Invitation> acceptInviteProject(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String token) throws Exception;

}
