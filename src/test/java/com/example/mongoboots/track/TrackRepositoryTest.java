package com.example.mongoboots.track;

import com.mongodb.client.MongoClients;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@RunWith(SpringRunner.class)
@DataMongoTest
@DirtiesContext
class TrackRepositoryTest {
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @BeforeEach
    public void setup() throws Exception {
        mongoTemplate.dropCollection(Track.class);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mongoTemplate.dropCollection(Track.class);
    }

    @Test
    void testSaveAndFindTrack() throws Exception {
        Track track1 = new Track("1", "blab-la","something");
        trackRepository.save(track1);

        assertEquals("1", trackRepository.findByTrackId("1").getId());
//        assertEquals(track2, mongoTemplate.findByTrackId("2"));
//        assertNull(trackRepository.findByTrackId("3"));
    }

}