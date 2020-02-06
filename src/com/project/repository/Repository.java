package com.project.repository;

import com.project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@org.springframework.stereotype.Repository
public interface Repository extends MongoRepository<User, Long> {
    User findByLogin(String login);
}
