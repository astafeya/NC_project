package com.project.repository;

import com.project.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {
    List<Comment> findByTextID(long textID);

    Comment findByTextIDAndCommentatorIDAndDate(long textID, long commentatorID, Date date);
}