package com.example.keyfinder;

import org.junit.Test;

import static org.junit.Assert.*;

public class ToneTest {

    @Test
    public void getDegree() {
        Tone curTone = new Tone(0, Tone.TONE_BASS);
        assertEquals(0, curTone.getDegree());

        curTone = new Tone(7, Tone.TONE_BASS);
        assertEquals(7, curTone.getDegree());

        curTone = new Tone(12, Tone.TONE_BASS);
        assertEquals(12, curTone.getDegree());
    }

    @Test
    public void getCode() {
        Tone curTone = new Tone(5, Tone.TONE_BASS);
        assertEquals(Tone.TONE_BASS, curTone.getCode());

        curTone = new Tone(5, Tone.TONE_CHORD);
        assertEquals(Tone.TONE_CHORD, curTone.getCode());
    }

    @Test
    public void toStringTest() {
        Tone curTone = new Tone(3, Tone.TONE_BASS);
        assertEquals("Bass 3", curTone.toString());

        curTone = new Tone(5, Tone.TONE_CHORD);
        assertEquals("Chord 5", curTone.toString());
    }
}