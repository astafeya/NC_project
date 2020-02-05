package com.project.sequence.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document (collection = "sequences")
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;
}
