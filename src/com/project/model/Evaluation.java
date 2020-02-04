package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document (collection = "evaluations")
public class Evaluation {
    @Id
    private Integer id;
    @NonNull
    private Integer textID;
    @NonNull
    private Integer evaluatorID;
    @NonNull
    private Date date;
}
