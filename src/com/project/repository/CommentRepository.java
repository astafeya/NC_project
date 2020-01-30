package com.project.repository;

import com.project.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Integer> {
    List<Comment> findByTextID(int textID);

    @Query(value = "{&and : [{'text_id' : ?0}, {'commentator_id' : ?1}, {'date' : ?2}]}")
    Comment find(int textID, int commentatorID, Date date); //?
}