package com.siaw.projectmanagementsystem.repository;

import com.siaw.projectmanagementsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //because we have findCommentByIssue from jpa but it's not so practical, we want to search by the id
    //of that issue not whole issue itself I guess 4h26min
    List<Comment> findCommentByIssueId(Long issueId);


}
