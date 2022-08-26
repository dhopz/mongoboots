package com.example.mongoboots.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/api/tracks/{id}")
    public ResponseEntity<HttpStatus> deleteTrack(@PathVariable("id") String id) {
        try {
            trackRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/tracks/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable("id") String id, @RequestBody TrackDTO trackDTO) {
        Optional<Track> trackData = trackRepository.findById(id);
        if (trackData.isPresent()) {
            Track newTrack = trackData.get();
            newTrack.setTitle(trackDTO.getTitle());
            newTrack.setArtist(trackDTO.getArtist());

            return new ResponseEntity<>(trackRepository.save(newTrack), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
