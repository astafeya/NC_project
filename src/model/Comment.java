package model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Comment {
    private int textID;
    private int commentatorID;
    @NonNull
    private Date date;
    @NonNull
    private String content;
}
