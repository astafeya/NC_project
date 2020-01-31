package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document (collection = "evaluations")
public class Evaluation {
    @NonNull
    private Integer textID;
    @NonNull
    private Integer evaluatorID;
    @NonNull
    private Date date;
}
