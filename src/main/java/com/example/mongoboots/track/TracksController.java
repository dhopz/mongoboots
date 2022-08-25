package com.example.mongoboots.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMongoRepositories
public class TracksController {

    @Autowired
    private TrackRepository trackRepository;

    @PostMapping("/api/tracks")
    public Track createTrack(@RequestBody TrackDTO trackDTO) {
        Track track = new Track(trackDTO.getId(),trackDTO.getTitle(),trackDTO.getArtist());
        return trackRepository.save(track);
    }

    @GetMapping("/api/tracks")
    public Iterable<Track> index() {
        return trackRepository.findAll();
    }

    @GetMapping("/api/tracks/title")
    public Track getTitle(@RequestBody TrackDTO trackDTO) {
        return trackRepository.findTrackByTitle(trackDTO.getTitle());
    }

    @GetMapping("/api/tracks/id")
    public Track getId(@RequestBody TrackDTO trackDTO) {
        return trackRepository.findByTrackId(trackDTO.getId());
    }

    @GetMapping("/api/tracks/artist")
    public List<Track> getArtist(@RequestBody TrackDTO trackDTO) {
        return trackRepository.findAll(trackDTO.getArtist());
    }


}
