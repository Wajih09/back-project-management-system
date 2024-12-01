package com.siaw.projectmanagementsystem.service;

import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Chat;
import com.siaw.projectmanagementsystem.model.Project;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, AppUser user) throws Exception;

    Project getProjectById(Long projectId) throws Exception;

    List<Project> getProjectByTeam(AppUser user, String category, String tag) throws Exception;

    Chat getChatByProjectId(Long projectId) throws Exception;

    Project updateProject(Project updatedProject, Long projectId) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception;

    void removeUserFromProject(Long projectId, Long userId) throws Exception;

    //for security purpose we need to check that project owner is requesting delete here and not any other user 2h17min
    void deleteProject(Long projectId, Long userId) throws Exception;

    List<Project> searchProjects(String keyword, AppUser user) throws Exception;
}
