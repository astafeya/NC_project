package com.project.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
public class Text {
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
}
