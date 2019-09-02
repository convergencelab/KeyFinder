package com.example.keyfinder;

import org.junit.Test;

import static org.junit.Assert.*;

public class MelodicMinorModeTest {

    // Melodic Minor (Root)
    @Test
    public void testMode0() {
        final int testIx = 0;
        final int[] testIntervals = {0, 2, 3, 5, 7, 9, 11};
        final String testName = "Melodic Minor";

        Mode mode = new MelodicMinorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, MusicTheory.MELODIC_MINOR_MODE_NAMES[testIx]);
    }

    // Phrygian #6
    @Test
    public void testMode1() {
        final int testIx = 1;
        final int[] testIntervals = {0, 1, 3, 5, 7, 9, 10};
        final String testName = "Phrygian " + MusicTheory.SHARP + "6";

        Mode mode = new MelodicMinorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, MusicTheory.MELODIC_MINOR_MODE_NAMES[testIx]);
    }

    // Lydian Augmented
    @Test
    public void testMode2() {
        final int testIx = 2;
        final int[] testIntervals = {0, 2, 4, 6, 8, 9, 11};
        final String testName = "Lydian Augmented";

        Mode mode = new MelodicMinorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, MusicTheory.MELODIC_MINOR_MODE_NAMES[testIx]);
    }

    // Lydian b7
    @Test
    public void testMode3() {
        final int testIx = 3;
        final int[] testIntervals = {0, 2, 4, 6, 7, 9, 10};
        final String testName = "Lydian " + MusicTheory.FLAT + "7";

        Mode mode = new MelodicMinorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, MusicTheory.MELODIC_MINOR_MODE_NAMES[testIx]);
    }

    // Mixolydian b6
    @Test
    public void testMode4() {
        final int testIx = 4;
        final int[] testIntervals = {0, 2, 4, 5, 7, 8, 10};
        final String testName = "Mixolydian " + MusicTheory.FLAT + "6";

        Mode mode = new MelodicMinorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, MusicTheory.MELODIC_MINOR_MODE_NAMES[testIx]);
    }

    // Locrian #2
    @Test
    public void testMode5() {
        final int testIx = 5;
        final int[] testIntervals = {0, 2, 3, 5, 6, 8, 10};
        final String testName = "Locrian " + MusicTheory.SHARP + "2";

        Mode mode = new MelodicMinorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, MusicTheory.MELODIC_MINOR_MODE_NAMES[testIx]);
    }

    // Altered
    @Test
    public void testMode6() {
        final int testIx = 6;
        final int[] testIntervals = {0, 1, 3, 4, 6, 8, 10};
        final String testName = "Altered";

        Mode mode = new MelodicMinorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, MusicTheory.MELODIC_MINOR_MODE_NAMES[testIx]);
    }

}