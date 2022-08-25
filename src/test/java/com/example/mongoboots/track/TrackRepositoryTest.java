package com.example.mongoboots.track;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataMongoTest(properties = {"spring.mongodb.embedded.version=4.0.2"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
    void testSaveAndFindTrack() {
        Track track1 = new Track("1", "blab-la","something");
        Track track2 = new Track("2", "blab-la-la","something else");
        trackRepository.save(track1);
        trackRepository.save(track2);

        assertEquals("1", trackRepository.findByTrackId("1").getId());
        assertEquals("2", trackRepository.findByTrackId("2").getId());
        assertNull(trackRepository.findByTrackId("3"));
    }

}