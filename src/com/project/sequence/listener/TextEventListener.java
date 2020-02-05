package com.project.sequence.listener;

import com.project.model.Text;
import com.project.sequence.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class TextEventListener extends AbstractMongoEventListener<Text> {
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public TextEventListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Text> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Text.SEQUENCE_NAME));
        }
    }
}
