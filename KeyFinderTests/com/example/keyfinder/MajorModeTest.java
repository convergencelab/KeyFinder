package com.example.keyfinder;

import org.junit.Test;

import static org.junit.Assert.*;

public class MajorModeTest {

    // Ionian (Root)
    @Test
    public void testMode0() {
        final int testIx = 0;
        final int[] testIntervals = {0, 2, 4, 5, 7, 9, 11};
        final String testName = "Ionian";

        Mode mode = new MajorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, mode.getName());
    }

    // Dorian
    @Test
    public void testMode1() {
        final int testIx = 1;
        final int[] testIntervals = {0, 2, 3, 5, 7, 9, 10};
        final String testName = "Dorian";

        Mode mode = new MajorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, mode.getName());
    }

    // Phrygian
    @Test
    public void testMode2() {
        final int testIx = 2;
        final int[] testIntervals = {0, 1, 3, 5, 7, 8, 10};
        final String testName = "Phrygian";

        Mode mode = new MajorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, mode.getName());
    }

    // Lydian
    @Test
    public void testMode3() {
        final int testIx = 3;
        final int[] testIntervals = {0, 2, 4, 6, 7, 9, 11};
        final String testName = "Lydian";

        Mode mode = new MajorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, mode.getName());
    }

    // Mixolydian
    @Test
    public void testMode4() {
        final int testIx = 4;
        final int[] testIntervals = {0, 2, 4, 5, 7, 9, 10};
        final String testName = "Mixolydian";

        Mode mode = new MajorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, mode.getName());
    }

    // Aeolian
    @Test
    public void testMode5() {
        final int testIx = 5;
        final int[] testIntervals = {0, 2, 3, 5, 7, 8, 10};
        final String testName = "Aeolian";

        Mode mode = new MajorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, mode.getName());
    }

    // Locrian
    @Test
    public void testMode6() {
        final int testIx = 6;
        final int[] testIntervals = {0, 1, 3, 5, 6, 8, 10};
        final String testName = "Locrian";

        Mode mode = new MajorMode(testIx);
        assertEquals(testIx, mode.getIx());
        assertArrayEquals(testIntervals, mode.getIntervals());
        assertEquals(testName, mode.getName());
    }

}