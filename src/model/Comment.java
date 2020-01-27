package model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Comment {
    @NonNull
    private int textID;
    @NonNull
    private int commentatorID;
    @NonNull
    private Date date;
    @NonNull
    private String content;
}
