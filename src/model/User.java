package model;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class User {
    @NonNull
    private int id;
    @NonNull
    private String login;
    @NonNull
    private String password;
    @NonNull
    private String eMail;
    private ArrayList<Text> texts;
}
