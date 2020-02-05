package com.project.service;

import com.project.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.UserRepository;

@Service
public class UserService {
    private static Logger log = LogManager.getLogger(UserService.class.getName());
    @Autowired
    private UserRepository userRepository;

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
}
