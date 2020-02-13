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
    private Date creationDate;
    @NonNull
    private Date lastEditDate;
    @NonNull
    private String content;
    private List<Comment> comments;
    private List<Evaluation> evaluations;
}
