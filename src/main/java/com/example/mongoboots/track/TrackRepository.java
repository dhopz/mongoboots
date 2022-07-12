package com.example.mongoboots.track;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track, String> {
    @Query("{name:'?0'}")
    Track findTrackByName(String name);

    @Query(value="{author:'?0'}", fields="{'name' : 1, 'author' : 1}")
    List<Track> findAll(String category);

    public long count();
}
