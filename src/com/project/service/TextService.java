package com.project.service;

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
import java.util.stream.Collectors;

@Service
public class TextService {
    private static Logger log = LogManager.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository repository;

    public Text add(String login, String title, Boolean visibility, String content) {
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

    public Text edit(String login, String title, Boolean visibility, Date creationDate, String content) {
        Text text = getText(login, creationDate);
        log.info("edit started: " + text);
        text.setTitle(title);
        text.setVisibility(visibility);
        text.setContent(content);
        Date lastEditDate = new Date();
        text.setLastEditDate(lastEditDate);
        setText(login, text);
        log.info("edit ended: " + text);
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

    public void delete(String login, Date creationDate) {
        log.info("delete: owner = " + login + ", creationDate = " + creationDate);
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        texts.removeIf(i -> i.getCreationDate().equals(creationDate));
        user.setTexts(texts);
        repository.save(user);
    }
}
