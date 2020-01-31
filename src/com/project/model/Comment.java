package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document (collection = "comments")
public class Comment {
    @NonNull
    private Integer textID;
    @NonNull
    private Integer commentatorID;
    @NonNull
    private Date date;
    @NonNull
    private String content;
}
