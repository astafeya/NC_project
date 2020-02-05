package com.project.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document (collection = "evaluations")
public class Evaluation {
    @Transient
    public static final String SEQUENCE_NAME = "evaluations_sequence";
    @Id
    private long id;
    @NonNull
    private Long textID;
    @NonNull
    private Long evaluatorID;
    @NonNull
    private Date date;
}
