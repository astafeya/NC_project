package com.project.repository;

import com.project.model.Text;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextRepository extends MongoRepository<Text, Integer> {
    @Query (value = "{&and: [{'ownerID' : ?0}, {'visibility' : 'true'}]}")
    List<Text> findVisibleTextByOwnerID(int ownerID);

    @Query (value = "{&and: [{'ownerID' : ?0}, {'visibility' : 'false'}]}")
    List<Text> findInvisibleTextByOwnerID(int ownerID);

    //List<Text> findByTitle(String title);
}
