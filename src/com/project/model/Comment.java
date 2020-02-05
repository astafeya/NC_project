package com.project.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document (collection = "comments")
public class Comment {
    @Transient
    public static final String SEQUENCE_NAME = "comments_sequence";
    @Id
    private long id;
    @NonNull
    private Long textID;
    @NonNull
    private Long commentatorID;
    @NonNull
    private Date date;
    @NonNull
    private String content;
}
