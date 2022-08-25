package com.example.mongoboots.track;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tracks")
public class Track {
    @Id
    private String id;
    private String title;
    private String artist;

    public Track(String id, String title, String artist) {
        super();
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String toString() {
        return String.format("Track[id=%s title='%s' artist='%s']", id, title, artist);
    }
}
