package com.project.service;

import com.project.model.Evaluation;
import com.project.model.Text;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EvaluationService {
    private static Logger log = LogManager.getLogger(EvaluationService.class.getName());
    @Autowired
    private UserRepository repository;

    private Text getText(String login, long id) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        Text text = texts.stream().filter(i -> i.getId() == id).findAny().get();
        return text;
    }

    private void setText(String login, Text text) {
        User user = repository.findByLogin(login);
        List<Text> texts = user.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            if (texts.get(i).getId() == text.getId()) {
                texts.set(i, text);
                break;
            }
        }
        user.setTexts(texts);
        repository.save(user);
    }

    public Evaluation add(String textOwnerLogin, long textId, String evaluatorLogin) {
        Date date = new Date();
        Evaluation evaluation = new Evaluation(evaluatorLogin, date);
        Text text = getText(textOwnerLogin, textId);
        List<Evaluation> evaluations = text.getEvaluations();
        if (evaluations == null) {
            evaluations = new ArrayList<>();
        }
        evaluations.add(evaluation);
        text.setEvaluations(evaluations);
        setText(textOwnerLogin, text);
        log.info("addEvaluation: textOwnerLogin = " + textOwnerLogin + ", textId = " + textId
                + ", evaluation = " + evaluation);
        return evaluation;
    }

    public boolean isEvaluated(String textOwnerLogin, long textId, String evaluatorLogin) {
        Text text = getText(textOwnerLogin, textId);
        List<Evaluation> evaluations = text.getEvaluations();
        Evaluation evaluation = evaluations.stream().filter(i -> i.getEvaluatorLogin().equals(evaluatorLogin))
                .findAny().orElse(null);
        if (evaluation != null) {
            log.info("isEvaluated: textOwnerLogin = " + textOwnerLogin + ", textId = "
                    + textId + ", evaluatorLogin = " + evaluatorLogin + ", result = true");
            return true;
        } else {
            log.info("isEvaluated: textOwnerLogin = " + textOwnerLogin + ", textId = "
                    + textId + ", evaluatorLogin = " + evaluatorLogin + ", result = false");
            return false;
        }
    }

    public int getEvaluation(String textOwnerLogin, long textId) {
        Text text = getText(textOwnerLogin, textId);
        List<Evaluation> evaluations = text.getEvaluations();
        if (evaluations == null) {
            log.info("getEvaluation: textOwnerLogin = " + textOwnerLogin + ", textId = " + textId +
                    ", result = " + 0);
            return 0;
        } else {
            log.info("getEvaluation: textOwnerLogin = " + textOwnerLogin + ", textId = " + textId +
                    ", result = " + evaluations.size());
            return evaluations.size();
        }
    }

    public void delete(String textOwnerLogin, long textId, String evaluatorLogin) {
        log.info("delete: owner = " + textOwnerLogin + ", textId = " + textId +
                ", evaluatorLogin = " + evaluatorLogin);
        Text text = getText(textOwnerLogin, textId);
        List<Evaluation> evaluations = text.getEvaluations();
        evaluations.removeIf(i -> i.getEvaluatorLogin().equals(evaluatorLogin));
        text.setEvaluations(evaluations);
        setText(textOwnerLogin, text);
    }
}
