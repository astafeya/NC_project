package model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Evaluation {
    private int textID;
    private int evaluatorID;
    @NonNull
    private Date date;
}
