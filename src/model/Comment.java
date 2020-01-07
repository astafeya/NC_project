package model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Comment {
    @NonNull
    private User user;
    @NonNull
    private Date date;
    @NonNull
    private String content;
}
