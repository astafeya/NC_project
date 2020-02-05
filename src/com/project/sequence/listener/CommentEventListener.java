package com.project.sequence.listener;

import com.project.model.Comment;
import com.project.sequence.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CommentEventListener extends AbstractMongoEventListener<Comment> {
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public CommentEventListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Comment> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Comment.SEQUENCE_NAME));
        }
    }
}
