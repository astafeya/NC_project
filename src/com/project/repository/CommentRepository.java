package com.project.repository;

import com.project.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Integer> {
    List<Comment> findByTextID(Integer textID);

    Comment findByTextIDAndCommentatorIDAndDate(Integer textID, Integer commentatorID, Date date);
}