package com.project.repository;

import com.project.model.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, Long> {
    int countByTextID(long textID);

    Evaluation findByTextIDAndEvaluatorID(long textID, long evaluatorID);
}
