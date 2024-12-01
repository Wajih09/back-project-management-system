package com.siaw.projectmanagementsystem.service.impl;

import com.siaw.projectmanagementsystem.model.AppUser;
import com.siaw.projectmanagementsystem.model.Comment;
import com.siaw.projectmanagementsystem.model.Issue;
import com.siaw.projectmanagementsystem.repository.AppUserRepository;
import com.siaw.projectmanagementsystem.repository.CommentRepository;
import com.siaw.projectmanagementsystem.repository.IssueRepository;
import com.siaw.projectmanagementsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        //4h27min
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new Exception("Issue not found with id = " + issueId));
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id = " + userId));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setIssue(issue);
        comment.setContent(content);
        comment.setCreatedDateTime(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        issue.getComments().add(savedComment);

        return savedComment;

    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
        //4h28min
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new Exception("Comment not found with id = " + commentId));
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id = " + userId));

        if(comment.getUser().equals(user)){
            commentRepository.delete(comment);
        } else {
            throw new Exception("User doesn't have permission to delete this comment !");
        }
    }

    @Override
    public List<Comment> findCommentByIssuedId(Long issueId) {
        return commentRepository.findCommentByIssueId(issueId);
    }
}
