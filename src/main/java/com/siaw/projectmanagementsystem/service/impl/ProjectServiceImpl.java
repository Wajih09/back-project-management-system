package com.siaw.projectmanagementsystem.service.impl;

import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Chat;
import com.siaw.projectmanagementsystem.model.Project;
import com.siaw.projectmanagementsystem.repository.ProjectRepository;
import com.siaw.projectmanagementsystem.service.AppUserService;
import com.siaw.projectmanagementsystem.service.ChatService;
import com.siaw.projectmanagementsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service //because here ProjectServiceImpl is a business logic class
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    ChatService chatService;

    @Override
    public Project createProject(Project project, AppUser user) throws Exception {
        Project createdProject = new Project();
        createdProject.setOwner(user);
        createdProject.setTags(project.getTags());
        createdProject.setName(project.getName());
        createdProject.setCategory(project.getCategory());
        createdProject.setDescription(project.getDescription());
        createdProject.getTeam().add(user);
        Project savedProject = projectRepository.save(createdProject);

        Chat chat = new Chat();
        chat.setProject(savedProject);
        Chat projectChat = chatService.createChat(chat);
        savedProject.setChat(projectChat);

        return savedProject;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> project = projectRepository.findById(projectId);//2h51min
        return(project.orElseThrow(() -> new Exception("Project with Id = " + project + "is not found !")));
    }

    @Override
    public List<Project> getProjectByTeam(AppUser user, String category, String tag) throws Exception {
        List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);

        if(category != null){
            projects = projects.stream().filter(project -> project.getCategory().equals(category))
                    .collect(Collectors.toList());
        }

        if(tag != null){
            projects = projects.stream().filter(project -> project.getTags().contains(tag))
                    .collect(Collectors.toList());
        }

        return projects;
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        Project project = getProjectById(projectId);

        return project.getChat();
    }

    @Override
    public Project updateProject(Project updatedProject, Long projectId) throws Exception {
        Project project = getProjectById(projectId);
        project.setTags(updatedProject.getTags());
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());

        return projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        AppUser user = appUserService.findUserById(userId);
        Project project = getProjectById(projectId);
        //changed condition in 4h29min https://www.youtube.com/watch?v=qis9sMaiqN4&t=12885s&ab_channel=CodeWithZosh
//        if(!project.getTeam().contains(user)){
//            project.getChat().getUsers().add(user);
//            project.getTeam().add(user);
//        }
//        projectRepository.save(project);
        for(AppUser member: project.getTeam()){
            if(member.getId().equals(userId)){
                return;
            }
        }
        project.getChat().getUsers().add(user);
        project.getTeam().add(user);
        projectRepository.save(project);

    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {
        AppUser user = appUserService.findUserById(userId);
        Project project = getProjectById(projectId);
        if(project.getTeam().contains(user)){
            project.getChat().getUsers().remove(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        getProjectById(projectId);
        projectRepository.deleteById(projectId);
    }

    @Override
    public List<Project> searchProjects(String keyword, AppUser user) throws Exception {
        //String partialName = "%" + keyword + "%"; 3h02min
        List<Project> projects = projectRepository.findByNameContainingAndTeamContains(keyword, user);

        return projects;
    }
}
