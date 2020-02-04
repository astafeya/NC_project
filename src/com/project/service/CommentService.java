package com.project.service;

import com.project.model.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.CommentRepository;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private static Logger log = LogManager.getLogger(CommentService.class.getName());
    @Autowired
    private CommentRepository commentRepository;

    public Comment create(Integer id, Integer textID, Integer commentatorID, String content) {
        Date date = new Date();
        Comment comment = commentRepository.save(new Comment(id, textID, commentatorID, date, content));
        log.info("create: " + comment);
        return comment;
    }

    public Comment edit(Integer textID, Integer commentatorID, Date date, String content) {
        Comment comment = commentRepository.findByTextIDAndCommentatorIDAndDate(textID, commentatorID, date);
        log.info("edit starts: " + comment);
        comment.setContent(content);
        log.info("edit ends: " + comment);
        return commentRepository.save(comment);
    }

    public List<Comment> getByTextID(Integer textID) {
        List<Comment> comments = commentRepository.findByTextID(textID);
        log.info("getByTextID: textID = " + textID + ", result = " + comments);
        return comments;
    }

    public void delete(Integer textID, Integer commentatorID, Date date) {
        log.info("delete: textID = " + textID + ", commentatorID = " + commentatorID +", date = " + date);
        commentRepository.delete(commentRepository.findByTextIDAndCommentatorIDAndDate(textID, commentatorID, date));
    }
}
