package model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
public class Text {
    @Id
    private int id;
    @NonNull
    private int ownerID;
    @NonNull
    private String title;
    @NonNull
    private boolean visibility;
    @NonNull
    private Date lastEditDate;
    @NonNull
    private String content;
    private int rating;
    private List<Comment> comments;
}
