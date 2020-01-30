package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document (collection = "evaluations")
public class Evaluation {
    private int textID;
    private int evaluatorID;
    @NonNull
    private Date date;
}
