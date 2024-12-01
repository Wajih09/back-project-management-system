package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.controller.api.ProjectApi;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Chat;
import com.siaw.projectmanagementsystem.model.Invitation;
import com.siaw.projectmanagementsystem.model.Project;
import com.siaw.projectmanagementsystem.request.InviteRequest;
import com.siaw.projectmanagementsystem.response.MessageResponse;
import com.siaw.projectmanagementsystem.service.AppUserService;
import com.siaw.projectmanagementsystem.service.InvitationService;
import com.siaw.projectmanagementsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//comment branche-a
//second comm
@RestController
public class ProjectController implements ProjectApi {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AppUserService userService;

    @Autowired
    private InvitationService invitationService;


    @Override
    public ResponseEntity<List<Project>> getProjects(String category, String tag, String jwt) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        List<Project> projects = projectService.getProjectByTeam(user,category,tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Project> getProjectById(Long projectId, String jwt) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Project> createProject(String jwt, Project project) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        Project createdProject = projectService.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Project> updateProject(Long projectId, String jwt, Project project) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        Project updatedProject = projectService.updateProject(project, projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteProject(Long projectId, String jwt) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        projectService.deleteProject(projectId, user.getId());
        MessageResponse res = new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Project>> searchProjects(String keyword, String jwt) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        List<Project> projects = projectService.searchProjects(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Chat> getChatProjectId(Long projectId, String jwt) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        Chat chat = projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResponse> inviteProject(String jwt, InviteRequest req) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());
        MessageResponse res = new MessageResponse("User invitation sent");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Invitation> acceptInviteProject(String jwt, String token) throws Exception {
        AppUser user = userService.findUserByJwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }

}
