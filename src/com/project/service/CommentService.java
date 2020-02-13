package com.project.service;

import com.project.model.Comment;
import com.project.model.Text;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private static Logger log = LogManager.getLogger(CommentService.class.getName());
    @Autowired
    private UserRepository repository;

    private Text getText(String login, long id) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        Text text = texts.stream().filter(i -> i.getId() == id).findAny().get();
        return text;
    }

    private void setText(String login, Text text) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getId() == text.getId()) {
                texts.set(i, text);
                break;
            }
        }
        user.setTexts(texts);
        repository.save(user);
    }

    public Comment add(String textOwnerLogin, long textId, String commentatorLogin, String content) {
        Date date = new Date();
        Text text = getText(textOwnerLogin, textId);
        Comment comment = new Comment(commentatorLogin, date, content);
        List<Comment> comments = text.getComments();
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        text.setComments(comments);
        setText(textOwnerLogin, text);
        log.info("addComment: textOwnerLogin = " + textOwnerLogin + ", textId = " + textId
                + ", comment = " + comment);
        return comment;
    }

    private Comment getComment(String textOwnerLogin, long textId, /* String commentatorLogin,*/
                               int commentNumber) {
        Text text = getText(textOwnerLogin, textId);
        List<Comment> comments = text.getComments();
        /*Comment comment = comments.stream().filter(i -> i.getCommentatorLogin().equals(commentatorLogin) &&
                i.getDate().equals(commentCreationDate)).findAny().get();*/
        Comment comment = comments.get(commentNumber);
        return comment;
    }

    private void setComment(String textOwnerLogin, long textId, Comment comment) {
        Text text = getText(textOwnerLogin, textId);
        List<Comment> comments = text.getComments();
        for (int i = 0; i < comments.size(); i++) {
            if (comment.getCommentatorLogin().equals(comment.getCommentatorLogin()) &&
                    comment.getDate().equals(comment.getDate())) {
                comments.set(i, comment);
                break;
            }
        }
        text.setComments(comments);
        setText(textOwnerLogin, text);
    }

    public Comment edit(String textOwnerLogin, long textId, /*String commentatorLogin,*/
                               int commentNumber, String content) {
        Comment comment = getComment(textOwnerLogin, textId, /* commentatorLogin,*/ commentNumber);
        log.info("editComment started: " + comment);
        comment.setContent(content);
        setComment(textOwnerLogin, textId, comment);
        log.info("editComment ended: " + comment);
        return comment;
    }

    public List<Comment> getAll(String textOwnerLogin, long textId) {
        List<Comment> comments = getText(textOwnerLogin, textId).getComments();
        log.info("getAll: owner = " + textOwnerLogin + ", textId = " + textId + ", result = " + comments);
        return comments;
    }

    public void delete(String textOwnerLogin, long textId, /*String commentatorLogin,*/
                              int commentNumber) {
        log.info("delete: owner = " + textOwnerLogin + ", textId = " + textId +
                /*", commentatorLogin = " + commentatorLogin + */", commentNumber = " + commentNumber);
        Text text = getText(textOwnerLogin, textId);
        List<Comment> comments = text.getComments();
        /*comments.removeIf(i -> i.getCommentatorLogin().equals(commentatorLogin) &&
                i.getDate().equals(commentCreationDate));*/
        comments.remove(commentNumber);
        text.setComments(comments);
        setText(textOwnerLogin, text);
    }
}
