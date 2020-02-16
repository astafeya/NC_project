package com.project.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Comment {
    @Id
    private long id;
    @NonNull
    private String commentatorLogin;
    @NonNull
    private String content;
    private Date date;
}
