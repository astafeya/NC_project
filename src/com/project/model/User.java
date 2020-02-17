package com.project.model;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Document (collection = "users")
public class User {
    @Id
    @NonNull
    @Length(min = 2)
    @UniqueElements(message = "This login is already taken.")
    private String login;
    @NonNull
    @Pattern(regexp = "^((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "The password must contain lowercase and uppercase Latin letters, numbers, and special characters.")
    @Length(min = 8, message = "The password must be at least 8 characters long.")
    private String password;
    //@NonNull
    @Email (regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            message = "The email address must match the format example@email.com")
    private String eMail;
    private long textNumber;
    private List<Text> texts;
}
