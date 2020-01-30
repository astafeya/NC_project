package com.project.service;

import com.project.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.CommentRepository;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment create(int textID, int commentatorID, String content) {
        Date date = new Date();
        return commentRepository.save(new Comment(textID, commentatorID, date, content));
    }

    public Comment edit(int textID, int commentatorID, Date date, String content) {
        Comment comment = commentRepository.find(textID, commentatorID, date);
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    public List<Comment> getByTextID(int textID) {
        return commentRepository.findByTextID(textID);
    }

    public void delete(int textID, int commentatorID, Date date) {
        commentRepository.delete(commentRepository.find(textID, commentatorID, date));
    }
}
