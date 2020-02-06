package com.project.service;

import com.project.model.Comment;
import com.project.model.Evaluation;
import com.project.model.Text;
import com.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.repository.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private static Logger log = LogManager.getLogger(Service.class.getName());
    @Autowired
    private Repository repository;

    // --------------------------------User--------------------------------

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

    public Text addText(String login, String title, Boolean visibility, String content) {
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
        return text;
    }

    public Text getText(String login, Date creationDate) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        int i;
        for (i = 0; i < texts.size(); i++) {
            if (texts.get(i).getCreationDate().equals(creationDate)) {
                break;
            }
        }
        Text text = texts.get(i);
        return text;
    }

    public List<Text> getTexts(String login) {
        User user = repository.findByLogin(login);
        return user.getTexts();
    }

    public void setText(String login, Text text) {
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

    public Text editText(String login, String title, Boolean visibility, Date creationDate, String content) {
        Text text = getText(login, creationDate);
        text.setTitle(title);
        text.setVisibility(visibility);
        text.setContent(content);
        Date lastEditDate = new Date();
        text.setLastEditDate(lastEditDate);
        setText(login, text);
        return text;
    }

    public List<Text> getVisibleTexts(String login) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        List<Text> visibleTexts = new ArrayList<>();
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getVisibility()) {
                visibleTexts.add(texts.get(i));
            }
        }
        return visibleTexts;
    }

    public List<Text> getInvisibleTexts(String login) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        List<Text> invisibleTexts = new ArrayList<>();
        for (int i = 0; i < texts.size(); i++) {
            if (!texts.get(i).getVisibility()) {
                invisibleTexts.add(texts.get(i));
            }
        }
        return invisibleTexts;
    }

    public void deleteText(String login, Date creationDate) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getCreationDate().equals(creationDate)) {
                texts.remove(i);
                break;
            }
        }
        user.setTexts(texts);
        repository.save(user);
    }

    // --------------------------------Comment--------------------------------

    public Comment addComment(String textOwnerLogin, Date textCreationDate, String commentatorLogin, String content) {
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
        return comment;
    }

    public Comment getComment(String textOwnerLogin, Date textCreationDate, String commentatorLogin,
                              Date commentCreationDate) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Comment> comments = text.getComments();
        int i;
        for (i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            if (comment.getCommentatorLogin().equals(commentatorLogin) &&
            comment.getDate().equals(commentCreationDate)) {
                break;
            }
        }
        Comment comment = comments.get(i);
        return comment;
    }

    public void setComment(String textOwnerLogin, Date textCreationDate, Comment comment) {
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
        comment.setContent(content);
        setComment(textOwnerLogin, textCreationDate, comment);
        return comment;
    }

    public void deleteComment(String textOwnerLogin, Date textCreationDate, String commentatorLogin,
                              Date commentCreationDate) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Comment> comments = text.getComments();
        int i;
        for (i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            if (comment.getCommentatorLogin().equals(commentatorLogin) &&
                    comment.getDate().equals(commentCreationDate)) {
                comments.remove(i);
                break;
            }
        }
        text.setComments(comments);
        setText(textOwnerLogin, text);
    }

    // --------------------------------Evaluation--------------------------------

    public Evaluation addEvaluation(String textOwnerLogin, Date textCreationDate, String evaluatorLogin) {
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
        return evaluation;
    }

    public boolean isEvaluated(String textOwnerLogin, Date textCreationDate, String evaluatorLogin) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Evaluation> evaluations = text.getEvaluations();
        for (int i = 0; i < evaluations.size(); i++) {
            if (evaluations.get(i).getEvaluatorLogin().equals(evaluatorLogin)) {
                return true;
            }
        }
        return false;
    }

    public void deleteEvaluation(String textOwnerLogin, Date textCreationDate, String evaluatorLogin) {
        Text text = getText(textOwnerLogin, textCreationDate);
        List<Evaluation> evaluations = text.getEvaluations();
        for (int i = 0; i < evaluations.size(); i++) {
            if (evaluations.get(i).getEvaluatorLogin().equals(evaluatorLogin)) {
                evaluations.remove(i);
                break;
            }
        }
        text.setEvaluations(evaluations);
        setText(textOwnerLogin, text);
    }
}
