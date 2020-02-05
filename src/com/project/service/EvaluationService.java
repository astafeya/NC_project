package com.project.service;

import com.project.model.Evaluation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.EvaluationRepository;

import java.util.Date;
import java.util.List;

@Service
public class EvaluationService {
    private static Logger log = LogManager.getLogger(EvaluationService.class.getName());
    @Autowired
    private EvaluationRepository evaluationRepository;

    public Evaluation create(Long textID, Long evaluatorID) {
        Date date = new Date();
        Evaluation evaluation = evaluationRepository.save(new Evaluation(textID, evaluatorID, date));
        log.info("create: " + evaluation.toString());
        return evaluation;
    }
    public boolean isEvaluated(Long textID, Long evaluatorID) {
        Evaluation evaluation = evaluationRepository.findByTextIDAndEvaluatorID(textID, evaluatorID);
        if (evaluation == null) {
            log.info("isEvaluated: textID = " + textID + ", evaluatorID = " + evaluatorID + ", result = false");
            return false;
        } else {
            log.info("isEvaluated: textID = " + textID + ", evaluatorID = " + evaluatorID + ", result = true");
            return true;
        }
    }

    public int getRating(Long textID) {
        int rating = evaluationRepository.countByTextID(textID);
        log.info("getRating: " + textID + " " + rating);
        return rating;
    }

    public void delete(Long textID, Long evaluatorID) {
        Evaluation evaluation = evaluationRepository.findByTextIDAndEvaluatorID(textID, evaluatorID);
        log.info("delete: textID = " + textID + ", evaluatorID = " + evaluatorID);
        evaluationRepository.delete(evaluation);
    }
}
