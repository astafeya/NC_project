package com.project.service;

import com.project.model.Comment;
import com.project.model.Evaluation;
import com.project.model.Text;
import com.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static Logger log = LogManager.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository repository;

    public User create(String login, String password, String eMail) {
        User user = repository.insert(new User(login, password, eMail));
        log.info("create user: " + user);
        return user;
    }

    public User edit(String login, String password, String eMail) {
        User user = repository.findByLogin(login);
        log.info("edit user started: " + user);
        user.setPassword(password);
        user.setEMail(eMail);
        log.info("edit user ended: " + user);
        return repository.save(user);
    }

    public User findByLogin(String login) {
        User user = repository.findByLogin(login);
        log.info("findByLogin: login = " + login + ", result = " + user);
        return user;
    }

    public void delete(String login) {
        log.info("delete user started: login = " + login);
        repository.delete(repository.findByLogin(login));
        log.info("delete user ended");
    }

    // --------------------------------Text--------------------------------

    /*public Text addText(String login, String title, Boolean visibility, String content) {
        Date creationDate = new Date();
        Text text = new Text (title, visibility, creationDate, creationDate, content);
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        if (texts == null) {
            texts = new ArrayList<>();
        }
        texts.add(text);
        user.setTexts(texts);
        repository.save(user);
        log.info("addText: ownerLogin = " + login + ", text = " + text);
        return text;
    }

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
        //texts.stream().filter(i -> i.getCreationDate().equals(text.getCreationDate()))
        user.setTexts(texts);
        repository.save(user);
    }

    public Text editText(String login, String title, Boolean visibility, Date creationDate, String content) {
        Text text = getText(login, creationDate);
        log.info("editText started: " + text);
        text.setTitle(title);
        text.setVisibility(visibility);
        text.setContent(content);
        Date lastEditDate = new Date();
        text.setLastEditDate(lastEditDate);
        setText(login, text);
        log.info("editText ended: " + text);
        return text;
    }

    public List<Text> getVisibleTexts(String login) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        List<Text> visibleTexts = texts.stream().filter(i -> i.getVisibility()).collect(Collectors.toList());
        log.info("getVisibleText: " + visibleTexts);
        return visibleTexts;
    }

    public List<Text> getInvisibleTexts(String login) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        List<Text> invisibleTexts = texts.stream().filter(i -> !i.getVisibility()).collect(Collectors.toList());
        log.info("getInvisibleText: " + invisibleTexts);
        return invisibleTexts;
    }

    public void deleteText(String login, Date creationDate) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        texts.removeIf(i -> i.getCreationDate().equals(creationDate));
        user.setTexts(texts);
        repository.save(user);
    }*/

    // --------------------------------Comment--------------------------------

    /*public Comment addComment(String textOwnerLogin, Date textCreationDate, String commentatorLogin, String content) {
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

    public Comment editComment(String textOwnerLogin, Date textCreationDate, String commentatorLogin,
                               Date commentCreationDate, String content) {
        Comment comment = getComment(textOwnerLogin, textCreationDate, commentatorLogin, commentCreationDate);
        log.info("editComment started: " + comment);
        comment.setContent(content);
        setComment(textOwnerLogin, textCreationDate, comment);
        log.info("editComment ended: " + comment);
        return comment;
    }

    public void deleteComment(String textOwnerLogin, Date textCreationDate, String commentatorLogin,
                              Date commentCreationDate) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Comment> comments = text.getComments();
        comments.removeIf(i -> i.getCommentatorLogin().equals(commentatorLogin) &&
                i.getDate().equals(commentCreationDate));
        text.setComments(comments);
        setText(textOwnerLogin, text);
    }*/

    // --------------------------------Evaluation--------------------------------

    /*public Evaluation addEvaluation(String textOwnerLogin, Date textCreationDate, String evaluatorLogin) {
        Date date = new Date();
        Evaluation evaluation = new Evaluation(evaluatorLogin, date);
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Evaluation> evaluations = text.getEvaluations();
        if (evaluations == null) {
            evaluations = new ArrayList<>();
        }
        evaluations.add(evaluation);
        text.setEvaluations(evaluations);
        setText(textOwnerLogin, text);
        log.info("addEvaluation: textOwnerLogin = " + textOwnerLogin + ", textCreationDate = " + textCreationDate
                + ", evaluation = " + evaluation);
        return evaluation;
    }

    public boolean isEvaluated(String textOwnerLogin, Date textCreationDate, String evaluatorLogin) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Evaluation> evaluations = text.getEvaluations();
        Evaluation evaluation = evaluations.stream().filter(i -> i.getEvaluatorLogin().equals(evaluatorLogin))
                .findAny().orElse(null);
        if (evaluation != null) {
            log.info("isEvaluated: textOwnerLogin = " + textOwnerLogin + ", textCreationDate = "
                    + textCreationDate + ", evaluatorLogin = " + evaluatorLogin + ", result = true");
            return true;
        } else {
            log.info("isEvaluated: textOwnerLogin = " + textOwnerLogin + ", textCreationDate = "
                    + textCreationDate + ", evaluatorLogin = " + evaluatorLogin + ", result = false");
            return false;
        }
    }

    public void deleteEvaluation(String textOwnerLogin, Date textCreationDate, String evaluatorLogin) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Evaluation> evaluations = text.getEvaluations();
        evaluations.removeIf(i -> i.getEvaluatorLogin().equals(evaluatorLogin));
        text.setEvaluations(evaluations);
        setText(textOwnerLogin, text);
    }*/
}
