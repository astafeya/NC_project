package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document (collection = "comments")
public class Comment {
    @Id
    private Integer id;
    @NonNull
    private Integer textID;
    @NonNull
    private Integer commentatorID;
    @NonNull
    private Date date;
    @NonNull
    private String content;
}
