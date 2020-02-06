package com.project.repository;

import com.project.model.Text;
import com.project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findByLogin(String login);
}
