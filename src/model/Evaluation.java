package model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Evaluation {
    @NonNull
    private int textID;
    @NonNull
    private int evaluatorID;
    @NonNull
    private Date date;
}
