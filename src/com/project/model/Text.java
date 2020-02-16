package com.project.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class Text {
    @Id
    private long id;
    @NonNull
    private String title;
    @NonNull
    private Boolean visibility;
    @NonNull
    private String content;
    private Date creationDate;
    private Date lastEditDate;
    private long commentsNumber;
    private List<Comment> comments;
    private List<Evaluation> evaluations;
}
