package com.siaw.projectmanagementsystem.service;

import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Issue;
import com.siaw.projectmanagementsystem.request.IssueRequest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IssueService {

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issueRequest, AppUser user) throws Exception;

    //Optional<Issue> updateIssue(Long issueId, IssueRequest updatedIssue, Long userId);

    void deleteIssue(Long issueId, Long userId) throws Exception;

    //List<Issue> getIssueByAssigneeId(Long assigneeId) throws  Exception;

    //List<Issue> searchIssues(String  title, String status, String priority, Long assigneeId) throws Exception;

    //List<AppUser> getAssigneeForIssue(Long issueId) throws Exception;

    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;


}
