package com.project.sequence.listener;

import com.project.model.Evaluation;
import com.project.sequence.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class EvaluationEventListener extends AbstractMongoEventListener<Evaluation> {
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public EvaluationEventListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Evaluation> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Evaluation.SEQUENCE_NAME));
        }
    }
}
