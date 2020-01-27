package model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class User {
    @Id
    private int id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private String eMail;
    private List<Text> texts;
}
