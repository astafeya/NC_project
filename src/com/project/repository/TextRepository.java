package com.project.repository;

import com.project.model.Text;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TextRepository extends MongoRepository<Text, Integer> {
    List<Text> findTextByOwnerIDAndVisibility(long ownerID, Boolean visibility);

    Text findByOwnerIDAndCreationDate(long ownerID, Date creationDate);
}
