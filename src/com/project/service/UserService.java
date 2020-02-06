package com.project.service;

import com.mongodb.LazyDBList;
import com.project.model.Text;
import com.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static Logger log = LogManager.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepository;

    // --------------------------------User--------------------------------

    public User create(String login, String password, String eMail) {
        User user = userRepository.save(new User(login, password, eMail));
        log.info("create: " + user);
        return user;
    }

    public User edit(String login, String password, String eMail) {
        User user = userRepository.findByLogin(login);
        log.info("edit started: " + user);
        user.setPassword(password);
        user.setEMail(eMail);
        log.info("edit ended: " + user);
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        log.info("findByLogin: login = " + login + ", result = " + user);
        return user;
    }

    public void delete(String login) {
        log.info("delete started: login = " + login);
        userRepository.delete(userRepository.findByLogin(login));
        log.info("delete ended");
    }

    // --------------------------------Text--------------------------------

    public Text addText(String login, String title, Boolean visibility, String content) {
        Date creationDate = new Date();
        Text text = new Text (title, visibility, creationDate, creationDate, content);
        User user = userRepository.findByLogin(login);
        List<Text> texts = user.getTexts();
        if (texts == null) {
            texts = new ArrayList<>();
        }
        texts.add(text);
        user.setTexts(texts);
        userRepository.save(user);
        return text;
    }

    public Text editText(String login, String title, Boolean visibility, Date creationDate, String content) {
        User user = userRepository.findByLogin(login);
        List<Text> texts = user.getTexts();
        int i;
        for (i = 0; i < texts.size(); i++) {
            if (texts.get(i).getCreationDate().equals(creationDate)) {
                break;
            }
        }
        Text text = texts.get(i);
        log.info("editText starts: " + text);
        text.setTitle(title);
        text.setVisibility(visibility);
        text.setContent(content);
        Date lastEditDate = new Date();
        text.setLastEditDate(lastEditDate);
        log.info("editText ends: " + text);
        texts.set(i, text);
        user.setTexts(texts);
        userRepository.save(user);
        return text;
    }

    /*public Text getText(String login, Date creationDate) {
        User user = userRepository.findByLogin(login);
        List<Text> texts = user.getTexts();
        int i;
        for (i = 0; i < texts.size(); i++) {
            if (texts.get(i).getCreationDate().equals(creationDate)) {
                break;
            }
        }
        Text text = texts.get(i);
        return text;
    }*/

    public List<Text> getVisibleTexts(String login) {
        User user = userRepository.findByLogin(login);
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
        User user = userRepository.findByLogin(login);
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
        User user = userRepository.findByLogin(login);
        List<Text> texts = user.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getCreationDate().equals(creationDate)) {
                texts.remove(i);
                break;
            }
        }
        user.setTexts(texts);
        userRepository.save(user);
    }
}
