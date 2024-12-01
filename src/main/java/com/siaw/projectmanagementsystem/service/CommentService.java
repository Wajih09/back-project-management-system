package com.siaw.projectmanagementsystem.service;

import com.siaw.projectmanagementsystem.model.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String content) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByIssuedId(Long issueId);
}
