package com.project.service;

import com.project.model.User;
import com.project.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User create(String login, String password, String eMail) {
        return userRepository.save(new User(login, password, eMail));
    }

    //public User edit()

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void delete(String login) {
        userRepository.delete(userRepository.findByLogin(login));
    }
}
