package com.project.model;

import lombok.Data;
import lombok.Generated;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.List;

@Data

@Document (collection = "users")
public class User {
    @Id
    private int id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    @Email
    private String eMail;
    @Transient
    private List<Text> visibleTexts;
    @Transient
    private List<Text> invisibleTexts;
}
