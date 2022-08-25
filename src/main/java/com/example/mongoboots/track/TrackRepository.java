package com.example.mongoboots.track;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;


public interface TrackRepository extends MongoRepository<Track, String> {

    @Query("{title:'?0'}")
    Track findTrackByTitle(String title);

    @Query("{id:'?0'}")
    Track findByTrackId(String id);

    @Query(value="{artist:'?0'}", fields="{'title' : 1, 'artist' : 1}")
    List<Track> findAll(String artist);

    public long count();

}
