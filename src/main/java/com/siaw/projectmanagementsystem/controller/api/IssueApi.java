package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.dto.IssueDto;
import com.siaw.projectmanagementsystem.model.Issue;
import com.siaw.projectmanagementsystem.request.IssueRequest;
import com.siaw.projectmanagementsystem.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/issues")
public interface IssueApi {

//    @GetMapping
//    ResponseEntity<List<Issue>> getAllIssues() throws Exception;

    @GetMapping("/{issueId}")
    ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception;

    @GetMapping("/project/{projectId}")
    ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception;

    @PostMapping
    ResponseEntity<IssueDto> createIssue(
            @RequestHeader("Authorization") String token,
            @RequestBody IssueRequest issueRequest) throws Exception;

//    @PutMapping("/{issueId}")
//    ResponseEntity<Issue> updateIssue(
//            @PathVariable Long issueId,
//            @RequestHeader("Authorization") String token,
//            @RequestBody IssueRequest updatedIssue) throws Exception;

    @DeleteMapping("/{issueId}")
    ResponseEntity<MessageResponse> deleteIssue(
            @PathVariable Long issueId,
            @RequestHeader("Authorization") String token) throws Exception;

//    @GetMapping("/search")
//    ResponseEntity<List<Project>> searchIssues(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String status,
//            @RequestParam(required = false) String priority,
//            @RequestParam(required = false) Long assigneeId) throws Exception;

    @PutMapping("/{issueId}/assignee/{userId}") //4h20min
    ResponseEntity<Issue> addUserToIssue(
            @PathVariable Long issueId,
            @PathVariable Long userId) throws Exception;

//    @GetMapping("/assignee/{assigneeId}")
//    ResponseEntity<List<Issue>> getIssuesByAssigneeId(@PathVariable Long assigneeId) throws Exception;

    @PutMapping("/{issueId}/status/{status}") //4h20min
    ResponseEntity<Issue> updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId) throws Exception;
}
