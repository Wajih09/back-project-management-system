package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.controller.api.IssueApi;
import com.siaw.projectmanagementsystem.dto.IssueDto;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Issue;
import com.siaw.projectmanagementsystem.request.IssueRequest;
import com.siaw.projectmanagementsystem.response.MessageResponse;
import com.siaw.projectmanagementsystem.service.AppUserService;
import com.siaw.projectmanagementsystem.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IssueController implements IssueApi {

    @Autowired
    private IssueService issueService;

    @Autowired
    private AppUserService appUserService;

    @Override
    public ResponseEntity<Issue> getIssueById(Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @Override
    public ResponseEntity<List<Issue>> getIssueByProjectId(Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @Override
    public ResponseEntity<IssueDto> createIssue(String token, IssueRequest issueRequest) throws Exception {
        AppUser tokenUser = appUserService.findUserByJwt(token);
        AppUser user = appUserService.findUserById(tokenUser.getId());

        Issue createdIssue = issueService.createIssue(issueRequest, tokenUser);
        IssueDto issueDto = new IssueDto();
        issueDto.setId(createdIssue.getId());
        issueDto.setTitle(createdIssue.getTitle());
        issueDto.setDescription(createdIssue.getDescription());
        issueDto.setStatus(createdIssue.getStatus());
        issueDto.setPriority(createdIssue.getPriority());
        issueDto.setDueDate(createdIssue.getDueDate());
        issueDto.setTags(createdIssue.getTags());
        issueDto.setAssignee(createdIssue.getAssignee());
        issueDto.setProject(createdIssue.getProject());

        return ResponseEntity.ok(issueDto);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteIssue(Long issueId, String token) throws Exception {
        AppUser user = appUserService.findUserByJwt(token);
        issueService.deleteIssue(issueId,user.getId());
        MessageResponse res = new MessageResponse();
        res.setMessage("Issue deleted");
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<Issue> addUserToIssue(Long issueId, Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);
        return ResponseEntity.ok(issue);
    }

//    @Override
//    public ResponseEntity<List<Issue>> getIssuesByAssigneeId(Long assigneeId) throws Exception {
//        List<Issue> issues = issueService.getIssuesByAssigneeId(assigneeId);
//        return ResponseEntity.ok(issues);
//    }

    @Override
    public ResponseEntity<Issue> updateIssueStatus(String status, Long issueId) throws Exception {
        Issue issue = issueService.updateStatus(issueId, status);
        return ResponseEntity.ok(issue);
    }




}
