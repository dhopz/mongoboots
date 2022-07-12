package com.example.mongoboots.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMongoRepositories
public class TracksController {

    @Autowired
    private TrackRepository trackRepository;

    @PostMapping("/api/tracks")
    public Track create(@RequestBody Track track) {
        return trackRepository.save(track);
    }
}
