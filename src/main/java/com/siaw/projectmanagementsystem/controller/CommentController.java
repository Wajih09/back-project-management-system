package com.siaw.projectmanagementsystem.controller;

import com.siaw.projectmanagementsystem.controller.api.CommentApi;
import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Comment;
import com.siaw.projectmanagementsystem.request.CreateCommentRequest;
import com.siaw.projectmanagementsystem.response.MessageResponse;
import com.siaw.projectmanagementsystem.service.AppUserService;
import com.siaw.projectmanagementsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController implements CommentApi{

    @Autowired
    private CommentService commentService;

    @Autowired
    private AppUserService appUserService;

    @Override
    public ResponseEntity<Comment> createComment(String jwt, CreateCommentRequest req) throws Exception {
        AppUser user = appUserService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComment(req.getIssueId(), user.getId(),
                req.getContent());
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteComment(String jwt, Long commentId) throws Exception {
        AppUser user = appUserService.findUserByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse res = new MessageResponse();
        res.setMessage("Comment deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Comment>> getCommentsByIssueId(Long issueId) throws Exception {
        List<Comment> comments = commentService.findCommentByIssuedId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


}
