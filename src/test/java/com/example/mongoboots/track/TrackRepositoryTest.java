package com.example.mongoboots.track;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@AutoConfigureDataMongo
@SpringBootTest
class TrackRepositoryTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setup() throws Exception {
        mongoTemplate.dropCollection(Track.class);
        Track track1 = new Track("1", "blab-la","something");
        Track track2 = new Track("2", "blab-la-la","something else");
        trackRepository.save(track1);
        trackRepository.save(track2);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(Track.class);
    }

    @Test
    void testSaveAndFindTrack() {
        assertEquals("1", trackRepository.findByTrackId("1").getId());
        assertEquals("2", trackRepository.findByTrackId("2").getId());
        assertNull(trackRepository.findByTrackId("3"));
    }

    @Test
    void TracksPostCreatesNewTrack() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/api/tracks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": \"10\",\"title\": \"Blue Line Swinger\", \"artist\": \"Yo La Tengo\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Blue Line Swinger"))
                .andExpect(jsonPath("$.artist").value("Yo La Tengo"));

        Track track = trackRepository.findByTrackId("10");
        assertEquals("Blue Line Swinger", track.getTitle());
        assertEquals("Yo La Tengo", track.getArtist());
    }

    @Test
    void TracksIndexReturnsTracks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/tracks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("blab-la"))
                .andExpect(jsonPath("$[0].artist").value("something"))
                .andExpect(jsonPath("$[1].title").value("blab-la-la"))
                .andExpect(jsonPath("$[1].artist").value("something else"));
    }

    @Test
    void TracksFindById() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.get("/api/tracks/id")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("blab-la"))
                .andExpect(jsonPath("$.artist").value("something"));
    }

    @Test
    void TracksFindByTitle() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.get("/api/tracks/title")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"title\": \"blab-la\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("blab-la"))
                .andExpect(jsonPath("$.artist").value("something"));
    }

    @Test
    void TracksFindByArtist() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.get("/api/tracks/artist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"artist\": \"something\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("blab-la"))
                .andExpect(jsonPath("$[0].artist").value("something"));
    }

//    A user complained that they made a typo once they added a new track and now
//    they can't seem to update this information.
    @Test
    void TracksFindByIdAndAmendTitleAndArtist() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.post("/api/tracks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": \"1\",\"title\": \"Blue Line Swinger\", \"artist\": \"Yo La Tengo\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Blue Line Swinger"))
                .andExpect(jsonPath("$.artist").value("Yo La Tengo"));
    }

    @Test
    void TracksFindAndDeleteById() throws Exception {
        Track track3 = new Track("3", "Title Delete","Artist Delete");
        trackRepository.save(track3);

        mvc.perform(
                        MockMvcRequestBuilders.delete("/api/tracks/" + track3.getId()))
                .andExpect(status().isNoContent());
    }

    //    A user complained that they made a typo once they added a new track and now
    //    they can't seem to update this information.
    @Test
    void TracksAmendTitleAndArtist() throws Exception {
        Track track3 = new Track("3", "Title Delete","Artist Delete");
        trackRepository.save(track3);

        mvc.perform(
                        MockMvcRequestBuilders.put("/api/tracks/" + track3.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": \"3\",\"title\": \"Blue Line Swinger\", \"artist\": \"Yo La Tengo\"}"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Track track = trackRepository.findByTrackId(track3.getId());
        assertEquals("Blue Line Swinger", track.getTitle());
        assertEquals("Yo La Tengo", track.getArtist());

    }

}