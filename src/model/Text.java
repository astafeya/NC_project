package model;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Text {
    @NonNull
    private int id;
    @NonNull
    private String title;
    @NonNull
    private boolean visibility;
    @NonNull
    private Date lastEditDate;
    @NonNull
    private String content;
    private int rating;
    private ArrayList<Comment> comments;
}
