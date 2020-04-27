package com.project.controller;

import com.project.model.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/registration")
    public void signUp(@RequestBody User user) {
        service.create(user.getLogin(), user.getPassword(), user.getEMail());
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> findUser(@PathVariable String login) {
        User user = service.findByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/edit")
    public void editUser(@RequestBody User user) {
        service.edit(user.getLogin(), user.getPassword(), user.getEMail());
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody String login) {
        service.delete(login);
    }
}
