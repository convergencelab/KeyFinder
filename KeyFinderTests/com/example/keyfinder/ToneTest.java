package com.example.keyfinder;

import com.example.keyfinder.harmony.BassTone;
import com.example.keyfinder.harmony.ChordTone;
import com.example.keyfinder.harmony.Tone;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToneTest {

    @Test
    public void testDegree0() {
        int testDegree = 0;

        Tone bassTone = new BassTone(testDegree);
        Tone chordTone = new ChordTone(testDegree);

        assertEquals(testDegree, bassTone.getDegree());
        assertEquals(testDegree, chordTone.getDegree());
    }

    @Test
    public void testDegree15() {
        int testDegree = 15;
        Tone bassTone = new BassTone(testDegree);
        Tone chordTone = new ChordTone(testDegree);

        assertEquals(testDegree, bassTone.getDegree());
        assertEquals(testDegree, chordTone.getDegree());
    }

    @Test
    public void testClassType() {
        Tone bassTone = new BassTone(0);
        Tone chordTone = new ChordTone(0);

        assertNotSame(bassTone.getClass(), chordTone.getClass());
    }

}