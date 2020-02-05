package com.project.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document (collection = "texts")
public class Text {
    @Transient
    public static final String SEQUENCE_NAME = "texts_sequence";
    @Id
    private long id;
    @NonNull
    private Long ownerID;
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
}
