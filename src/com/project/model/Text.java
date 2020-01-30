package com.project.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document (collection = "texts")
public class Text {
    @Id
    private int id;
    private int ownerID;
    @NonNull
    private String title;
    private boolean visibility;
    @NonNull
    private Date lastEditDate;
    @NonNull
    private String content;
    @Transient
    private int rating;
    @Transient
    private List<Comment> comments;
}
