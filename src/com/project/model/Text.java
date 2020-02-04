package com.project.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document (collection = "texts")
public class Text {
    @Id
    private Integer id;
    @NonNull
    private Integer ownerID;
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
