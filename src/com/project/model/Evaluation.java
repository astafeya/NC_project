package com.project.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class Evaluation {
    @NonNull
    private String evaluatorLogin;
    @NonNull
    private Date date;
}
