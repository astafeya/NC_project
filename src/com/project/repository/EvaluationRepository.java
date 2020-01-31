package com.project.repository;

import com.project.model.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, Integer> {
    int countByTextID(Integer textID);

    @Query(value = "{&and : [{'text_id' : ?0}, {'evaluator_id' : ?1}]}")
    Evaluation findByTextIDAndEvaluatorID(Integer textID, Integer evaluatorID);
}
