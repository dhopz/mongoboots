package com.example.mongoboots.track;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TrackTest {
    @Test
    void testConstructs() {
        Track subject = new Track("1","Hello, world!", "Sarah");
        assertEquals("Hello, world!", subject.getTitle());
        assertEquals("Sarah", subject.getArtist());
        assertEquals("1",subject.getId());
    }

    @Test
    void testToString() {
        String result = "Track[id=3 title='Hello, world!' artist='Sarah']";
        Track subject = new Track("3","Hello, world!", "Sarah");
        assertEquals(result, subject.toString());
    }

}
