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
}
