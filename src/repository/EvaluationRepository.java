package repository;

import model.Evaluation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends MongoRepository<Evaluation, Integer> {
    boolean existsByEvaluatorID(int evaluatorID);
    int countByTextID(int textID);
    void deleteByEvaluatorID(int evaluatorID);
}
