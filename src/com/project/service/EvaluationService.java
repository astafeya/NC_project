package com.project.service;

import com.project.model.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.EvaluationRepository;

import java.util.Date;
import java.util.List;

@Service
public class EvaluationService {
    @Autowired
    private EvaluationRepository evaluationRepository;

    public Evaluation create(Integer textID, Integer evaluatorID) {
        Date date = new Date();
        return evaluationRepository.save(new Evaluation(textID, evaluatorID, date));
    }
    public boolean isEvaluated(Integer textID, Integer evaluatorID) {
        if (evaluationRepository.findByTextIDAndEvaluatorID(textID, evaluatorID) == null) {
            return false;
        } else {
            return true;
        }
    }

    public int getRating(Integer textID) {
        return evaluationRepository.countByTextID(textID);
    }

    public void delete(Integer textID, Integer evaluatorID) {
        evaluationRepository.delete(evaluationRepository.findByTextIDAndEvaluatorID(textID, evaluatorID));
    }
}
