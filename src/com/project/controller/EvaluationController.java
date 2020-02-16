package com.project.controller;

import com.project.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{login}/{textId}")
public class EvaluationController {
    @Autowired
    EvaluationService service;

    @GetMapping("/evaluation")
    public int getEvaluation(@PathVariable String login, @PathVariable long textId) {
        return service.getEvaluation(login, textId);
    }

    @PostMapping("/newEvaluation")
    public void addEvaluation(@PathVariable String login, @PathVariable long textId, @RequestBody String evaluatorLogin) {
        service.add(login, textId, evaluatorLogin);
    }

    @GetMapping("/isEvaluated")
    public boolean isEvaluated(@PathVariable String login, @PathVariable long textId, @RequestBody String evaluatorLogin) {
        return service.isEvaluated(login, textId, evaluatorLogin);
    }

    @DeleteMapping("/deleteEvaluation")
    public void deleteEvaluation(@PathVariable String login, @PathVariable long textId, @RequestBody String evaluatorLogin) {
        service.delete(login, textId, evaluatorLogin);
    }
}
