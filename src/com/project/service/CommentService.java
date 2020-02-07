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
    private static Logger log = LogManager.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository repository;

    private Text getText(String login, Date creationDate) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        Text text = texts.stream().filter(i -> i.getCreationDate().equals(creationDate)).findAny().get();
        return text;
    }

    private void setText(String login, Text text) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getCreationDate().equals(text.getCreationDate())) {
                texts.set(i, text);
                break;
            }
        }
        user.setTexts(texts);
        repository.save(user);
    }

    public Comment add(String textOwnerLogin, Date textCreationDate, String commentatorLogin, String content) {
        Date date = new Date();
        Text text = getText(textOwnerLogin, textCreationDate);
        Comment comment = new Comment(commentatorLogin, date, content);
        List<Comment> comments = text.getComments();
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        text.setComments(comments);
        setText(textOwnerLogin, text);
        log.info("addComment: textOwnerLogin = " + textOwnerLogin + ", textCreationDate = " + textCreationDate
                + ", comment = " + comment);
        return comment;
    }

    private Comment getComment(String textOwnerLogin, Date textCreationDate, String commentatorLogin,
                               Date commentCreationDate) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Comment> comments = text.getComments();
        Comment comment = comments.stream().filter(i -> i.getCommentatorLogin().equals(commentatorLogin) &&
                i.getDate().equals(commentCreationDate)).findAny().get();
        return comment;
    }

    private void setComment(String textOwnerLogin, Date textCreationDate, Comment comment) {
        Text text = getText(textOwnerLogin, textCreationDate);
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

    public Comment edit(String textOwnerLogin, Date textCreationDate, String commentatorLogin,
                               Date commentCreationDate, String content) {
        Comment comment = getComment(textOwnerLogin, textCreationDate, commentatorLogin, commentCreationDate);
        log.info("editComment started: " + comment);
        comment.setContent(content);
        setComment(textOwnerLogin, textCreationDate, comment);
        log.info("editComment ended: " + comment);
        return comment;
    }

    public void delete(String textOwnerLogin, Date textCreationDate, String commentatorLogin,
                              Date commentCreationDate) {
        log.info("delete: owner = " + textOwnerLogin + ", creationDate = " + textCreationDate +
                ", commentatorLogin = " + commentatorLogin + ", commentCreationDate = " + commentCreationDate);
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Comment> comments = text.getComments();
        comments.removeIf(i -> i.getCommentatorLogin().equals(commentatorLogin) &&
                i.getDate().equals(commentCreationDate));
        text.setComments(comments);
        setText(textOwnerLogin, text);
    }
}
