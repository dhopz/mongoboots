package com.example.mongoboots.track;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tracks")
public class Track {
    @Id
    private String id;

    private String name;
    private String author;

    public Track(String id, String name, String author) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
