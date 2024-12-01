package com.siaw.projectmanagementsystem.controller.api;

import com.siaw.projectmanagementsystem.model.Comment;
import com.siaw.projectmanagementsystem.request.CreateCommentRequest;
import com.siaw.projectmanagementsystem.request.IssueRequest;
import com.siaw.projectmanagementsystem.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/comments")
public interface CommentApi {

    @PostMapping
    ResponseEntity<Comment> createComment(
            @RequestHeader("Authorization") String jwt,
            @RequestBody CreateCommentRequest req) throws Exception;

    @DeleteMapping("/{commentId}")
    ResponseEntity<MessageResponse> deleteComment(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long commentId) throws Exception;

    @GetMapping("/{issueId}")
    ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) throws Exception;
}
